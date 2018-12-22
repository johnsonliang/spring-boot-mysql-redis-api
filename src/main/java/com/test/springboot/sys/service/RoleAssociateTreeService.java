package com.test.springboot.sys.service;

import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.sys.dao.RoleAssociateTreeDao;
import com.test.springboot.sys.entity.QueryRoleAssociateTree;
import com.test.springboot.sys.entity.RoleAssociateTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 **/
@Service("roleAssociateTreeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class RoleAssociateTreeService extends GenericService<RoleAssociateTree, QueryRoleAssociateTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;
	@Override
	protected GenericDao<RoleAssociateTree, QueryRoleAssociateTree> getDao() {
		return roleAssociateTreeDao;
	}
}