package com.test.springboot.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.common.base.constant.SystemStaticConst;
import com.test.springboot.common.base.controller.GenericController;
import com.test.springboot.common.base.dto.Result;
import com.test.springboot.common.util.dict.DictCache;
import com.test.springboot.common.util.json.JsonHelper;
import com.test.springboot.common.util.redis.RedisCache;
import com.test.springboot.sys.entity.Dict;
import com.test.springboot.sys.entity.QueryDict;
import com.test.springboot.sys.service.DictService;

/*
* 类描述：
* @create 2017/10/9 0009 
*/
@RestController
@RequestMapping("/dict")
public class DictController extends GenericController<Dict, QueryDict> {

    @Autowired
    private DictService dictService;
    @Autowired
    private RedisCache redisCache;

    @Override
    protected DictService getService() {
        return dictService;
    }

    @PostMapping("/redisTest")
    public Result<?> redisTest(String key, String value) {
        List<Dict> dicts = new ArrayList<Dict>();
        Dict dict = new Dict();
        dict.setCode("高达哈工大");
        dicts.add(dict);
        redisCache.setList("dicts", dicts);
        List<Dict> dictss = redisCache.getList("dicts", new Dict());
        System.out.println("redisCache.getList(\"dicts\",Dict.class===>" + JsonHelper.list2json(dictss));
        redisCache.setString(key, value);
        System.out.println("redisCache.getString(key)===>" + redisCache.getString(key));
        return Result.success();
    }

    /**
     * 功能描述：将字典数据初始化到前端js中
     * 
     * @return
     */
    @PostMapping("/loadDict")
    public Map<String, Object> loadDict() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Dict> dictList = dictService.query(new QueryDict("1"));
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", dictList);
        return result;
    }

    /**
     * 功能描述：重新加载数据字典的数据到内存中
     * 
     * @return
     */
    @PostMapping("/reload")
    public Result<?> reload() {
        List<Dict> dictList = dictService.query(null);
        DictCache.reload(dictList);
        return Result.success();
    }

}
