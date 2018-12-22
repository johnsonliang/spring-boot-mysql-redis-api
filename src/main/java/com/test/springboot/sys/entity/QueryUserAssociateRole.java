package com.test.springboot.sys.entity;


import com.test.springboot.common.base.entity.QueryBase;

/**
 *
 **/
public class QueryUserAssociateRole extends QueryBase {
	private Integer userId;
	private Long roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
