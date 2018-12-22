package com.test.springboot.sys.controller;


import com.test.springboot.common.base.constant.SystemStaticConst;
import com.test.springboot.common.base.controller.GenericController;
import com.test.springboot.common.base.dto.Result;
import com.test.springboot.common.base.entity.Page;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.sys.entity.OrgGroup;
import com.test.springboot.sys.entity.QueryOrgGroup;
import com.test.springboot.sys.entity.QueryUser;
import com.test.springboot.sys.service.OrgGroupService;
import com.test.springboot.sys.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：组织架构的操作类

* @create 2017/9/30 0030
*/
@Controller
@RequestMapping("/group")
public class OrgGroupController extends GenericController<OrgGroup,QueryOrgGroup> {

    @Inject
    private OrgGroupService orgGroupService;
    @Inject
    private UserService userService;

    @Override
    protected GenericService<OrgGroup, QueryOrgGroup> getService() {
        return orgGroupService;
    }

  
    @Override
    public Result<?> update(OrgGroup entity) throws Exception {
        Map<String,Object> result = new HashMap<String, Object>();
        OrgGroup update = new OrgGroup();
        update.setGroupId(entity.getGroupId());
        update = orgGroupService.get(update);
        update.setName(entity.getName());
        update.setGroupCode(entity.getGroupCode());
        update.setNum(entity.getNum());
        boolean success = orgGroupService.update(update)!=null?true:false;
      
        return success ? Result.success(entity, "成功！") : Result.failure("500", "失败！");
    }

    @Override
    public Result<?> save(OrgGroup entity) throws Exception  {
        String max_node = getMaxNode(orgGroupService.getMaxOrgGroup(entity.getOrgGroup().getNode()),entity.getOrgGroup().getNode());
        entity.setParentNode(entity.getOrgGroup().getNode());
        entity.setNode(max_node);
        return super.save(entity);
    }

    /**
     * 功能描述：获取组织架构底下的相应的用户
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> userList(QueryUser user){
        Map<String,Object> result = new HashMap<String, Object>();
        Page page = userService.findByGroupUserPage(user);
        result.put("totalCount",page.getTotal());
        result.put("result",page.getRows());
        return result;
    }

    /**
     * 功能描述：获取组织架构的整颗菜单树
     * @return
     */
    @RequestMapping(value = "/loadGroupTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadGroupTree(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<OrgGroup> orgGroupList = orgGroupService.query(null);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"加载组织机构数据成功！");
        result.put("data",orgGroupList);
        return result;
    }

    private String getMaxNode(String node,String parentNode){
        String max_node = "";
        if(node==null){
            max_node = parentNode + "001";
        }else{
            String n = (Integer.parseInt(node.substring(node.length()-3)) + 1) + "";
            switch(n.length()){
                case 1:
                    max_node = parentNode + "00" + n;
                    break;
                case 2:
                    max_node = parentNode + "0" + n;
                    break;
                case 3:
                    max_node = parentNode + "" + n;
                    break;
            }
        }
        return max_node;
    }

}
