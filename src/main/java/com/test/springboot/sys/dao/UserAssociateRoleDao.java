package com.test.springboot.sys.dao;


import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.sys.entity.QueryUserAssociateRole;
import com.test.springboot.sys.entity.User;
import com.test.springboot.sys.entity.UserAssociateRole;

/**
 *
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}