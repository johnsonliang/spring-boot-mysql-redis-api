package com.test.springboot.sys.controller;


import com.test.springboot.common.base.constant.SystemStaticConst;
import com.test.springboot.common.base.controller.GenericController;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.sys.entity.QueryTree;
import com.test.springboot.sys.entity.Tree;
import com.test.springboot.sys.mapper.TreeMapper;
import com.test.springboot.sys.service.TreeService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 类描述：菜单操作controller
*/
@Controller
@RequestMapping("/tree")
public class TreeController extends GenericController<Tree,QueryTree> {

    @Inject
    private TreeService treeService;
    @Inject
    private TreeMapper treeMapper;

    @Override
    protected GenericService<Tree, QueryTree> getService() {
        return treeService;
    }
 
 
    /**
     * 功能描述：直接加载整个菜单树的数据(且必须要有管理员权限才可以加载该菜单树的数据)
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/loadUserTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadUserTree(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Tree> treeList = treeService.query(null);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"加载菜单数据成功！");
        result.put("data",treeMapper.treesToTressDTOs(treeList));
        return result;
    }


}
