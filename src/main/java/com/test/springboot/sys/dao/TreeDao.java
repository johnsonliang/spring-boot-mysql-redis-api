package com.test.springboot.sys.dao;


import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.sys.entity.QueryTree;
import com.test.springboot.sys.entity.Tree;
import com.test.springboot.sys.entity.User;

import java.util.List;

/**
 *
 **/
public interface TreeDao extends GenericDao<Tree, QueryTree> {

    /**
     * 功能描述：加载用户的菜单树的数据
     * @param user
     * @return
     */
	List<Tree> loadUserTree(User user);
}