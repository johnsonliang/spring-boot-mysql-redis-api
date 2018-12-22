package com.test.springboot.sys.entity;


import com.test.springboot.common.base.entity.QueryBase;

/**
 *
 **/
public class QueryRoleAssociateTree extends QueryBase {
	private Long roleId;
	private Long treeId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getTreeId() {
		return treeId;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

}
