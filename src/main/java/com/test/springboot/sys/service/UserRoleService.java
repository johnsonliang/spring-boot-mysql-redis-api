package com.test.springboot.sys.service;

import com.test.springboot.common.base.dao.GenericDao;
import com.test.springboot.common.base.service.GenericService;
import com.test.springboot.sys.dao.RoleAssociateTreeDao;
import com.test.springboot.sys.dao.UserRoleDao;
import com.test.springboot.sys.entity.QueryUserRole;
import com.test.springboot.sys.entity.RoleAssociateTree;
import com.test.springboot.sys.entity.Tree;
import com.test.springboot.sys.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 **/
@Service("userRoleService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserRoleService extends GenericService<UserRole, QueryUserRole> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserRoleDao userRoleDao;
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;

	@Override
	protected GenericDao<UserRole, QueryUserRole> getDao() {
		return userRoleDao;
	}

	/**
	 * 功能描述：获取权限菜单数据
	 * @param entity
	 * @return
	 */
	public UserRole getUserRoleAssociate(UserRole entity){
		return userRoleDao.getUserRoleAssociate(entity);
	}

	/**
	 * 功能描述：删除角色数据
	 * @param entityList
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean removeBath(List<UserRole> entityList) throws Exception {
        for(UserRole userRole:entityList){
			roleAssociateTreeDao.removeTreeByRoleId(userRole);
		}
		return super.removeBath(entityList);
	}

	/**
	 * 增加角色数据
	 * @param entity 保存对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean save(UserRole entity) throws Exception {
		entity.packagingTrees(entity.getTreeArray());
		List<Tree> treeList = entity.getTreeList();
		boolean success = super.save(entity);
		for(Tree tree:treeList){
			roleAssociateTreeDao.save(new RoleAssociateTree(entity.getId(),tree.getId()));
		}
		return success;
	}

	@Override
	public UserRole update(UserRole entity) throws Exception {
		entity.packagingTrees(entity.getTreeArray());
		List<Tree> treeList = entity.getTreeList();
		roleAssociateTreeDao.removeTreeByRoleId(entity);
		for(Tree tree:treeList){
			roleAssociateTreeDao.save(new RoleAssociateTree(entity.getId(),tree.getId()));
		}
		return super.update(entity);
	}
}