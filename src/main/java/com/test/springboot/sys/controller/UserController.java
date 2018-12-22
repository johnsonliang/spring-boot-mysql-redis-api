package com.test.springboot.sys.controller;


import com.test.springboot.common.base.constant.SystemStaticConst;
import com.test.springboot.common.base.controller.GenericController;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.common.util.json.JsonHelper;
import com.test.springboot.common.util.user.UserInfo;
import com.test.springboot.sys.entity.QueryUser;
import com.test.springboot.sys.entity.Tree;
import com.test.springboot.sys.entity.User;
import com.test.springboot.sys.entity.UserRole;
import com.test.springboot.sys.service.TreeService;
import com.test.springboot.sys.service.UserAssociateRoleService;
import com.test.springboot.sys.service.UserRoleService;
import com.test.springboot.sys.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
* 类描述：用户维护controller

* @create 2017/9/7 0007
*/
@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User,QueryUser> {

    @Inject
    private UserService userService;
    @Inject
    private TreeService treeService;
    @Inject
    private UserRoleService userRoleService;
    @Inject
    private UserAssociateRoleService userAssociateRoleService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }

    /**
     * 功能描述：加载首页菜单节点的数据
     * @return
     */
    @RequestMapping(value="/mainTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> mainTree(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Tree> trees = UserInfo.loadUserTree(treeService);
        result.put("data",trees);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

    /**
     * 功能描述：更新用户状态为禁用/启用
     * @param entity
     * @return
     */
    @RequestMapping(value="/userControl",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> userControl(User entity) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        if(userService.userControl(entity)){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"更新用户状态成功！");
            result.put("entity",entity);
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"更新用户状态失败！");
        }
        return result;
    }


    /**
     * 功能描述：加载所有的权限数据
     * @return
     */
    @RequestMapping(value = "/loadRoles",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadRoles(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<UserRole> userRoleList = userRoleService.query(null);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put("list",userRoleList);
        return result;
    }

}
