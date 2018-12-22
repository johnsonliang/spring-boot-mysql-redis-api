package com.test.springboot.sys.dao;


import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.sys.entity.QueryUserRole;
import com.test.springboot.sys.entity.UserRole;

/**
 *
 **/
public interface UserRoleDao extends GenericDao<UserRole, QueryUserRole> {

    /**
     * 功能描述：获取权限菜单数据
     * @param entity
     * @return
     */
    UserRole getUserRoleAssociate(UserRole entity);
	
}