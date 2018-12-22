package com.test.springboot.common.base.controller;

import com.test.springboot.common.base.dto.Result;
import com.test.springboot.common.base.entity.Page;
import com.test.springboot.common.base.entity.QueryBase;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.common.util.json.JsonHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericController<T, Q extends QueryBase> {

    // 抽象方法
    protected abstract GenericService<T, Q> getService();

    /**
     * Controller基路径
     */
    protected String basePath;

    /**
     * 功能描述：保存数据字典数据
     *
     * @param entity
     * @return
     */
    @PostMapping("/save")
    public Result<?> save(T entity) throws Exception {
        boolean success = getService().save(entity);
        return success ? Result.success(entity, "增加数据成功！") : Result.failure("500", "增加数据失败！");

    }

    /**
     * 功能描述：更新数据字典数据
     *
     * @param entity
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<?> update(T entity) throws Exception {
        boolean success = (getService().update(entity) != null) ? true : false;
        return success ? Result.success(entity, "增加数据成功！") : Result.failure("500", "增加数据失败！");
    }

    /**
     * 功能描述：实现批量删除数据字典的记录
     *
     * @param entity
     * @return
     */
    @PostMapping("/remove")
    public Result<?> remove(T entity) throws Exception {
        getService().delete(entity);
        return Result.success("删除数据成功！");
    }

    /**
     * 功能描述：实现批量删除数据字典的记录
     *
     * @param json
     * @return
     */
    @PostMapping("/removeBath")
    public Result<?> removeBath(String json) throws Exception {
        getService().removeBath((List<T>) JsonHelper.toList(json,
                (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
        return Result.success("批量删除数据成功！");
    }

    /**
     * 功能描述：获取数据字典的分页的数据
     *
     * @param entity
     * @return
     */
    @PostMapping("/list")
    public Result<?> list(Q entity) {
        Page page = getService().findByPage(entity);
        return Result.success(page);
    }

    /**
     * 功能描述：判断当前的字典元素是否已经存在
     *
     * @param entity
     * @return
     */
    @PostMapping("/isExist")
    public Result<?> isExist(Q entity) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (getService().query(entity).size() > 0) {
            result.put("valid", false);
        } else {
            result.put("valid", true);
        }
        return Result.success(result);
    }

    /**
     * 将首字母变小写
     *
     * @param str
     * @return
     */
    private static String toFirstCharLowerCase(String str) {
        char[] columnCharArr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (i == 0) {
                sb.append(Character.toLowerCase(cur));
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

}
