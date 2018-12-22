package com.test.springboot.sys.dao;


import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.sys.entity.QueryRoleAssociateTree;
import com.test.springboot.sys.entity.RoleAssociateTree;
import com.test.springboot.sys.entity.Tree;
import com.test.springboot.sys.entity.UserRole;

/**
 *
 **/
public interface RoleAssociateTreeDao extends GenericDao<RoleAssociateTree, QueryRoleAssociateTree> {

    /**
     * 功能描述：根据菜单ID来删除关联的菜单数据
     * @param tree
     * @return
     */
    int removeTreeByTreeId(Tree tree);

    /**
     * 功能描述：根据角色ID来删除关联的菜单数据
     * @param userRole
     * @return
     */
    int removeTreeByRoleId(UserRole userRole);
	
}