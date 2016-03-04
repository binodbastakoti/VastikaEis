package com.vastikaEIS.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.RoleDao;
import com.vastikaEIS.domain.Permissions;
import com.vastikaEIS.domain.RolePermissions;
import com.vastikaEIS.domain.Roles;
import com.vastikaEIS.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	public long addRoles(Roles roles) {

		return roleDao.addRole(roles);
	}

	@Override
	public Roles getRoleById(long id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public boolean deleteRole(long id) {
		Roles roles = roleDao.getRoleById(id);
		return roleDao.deleteRole(roles);
	}

	@Override
	public List<Roles> listAll() {
		return roleDao.listAll();
	}

	@Override
	public List<Permissions> listAllPermissions() {

		return roleDao.listAllPermissions();
	}

	@Override
	public long assignRolePermissions(List<Long> permissions, long roleId) {
		return roleDao.assignRolePermissions(permissions, roleId);
	}

	public List<RolePermissions> getRoleMembersByRoleId(long roleId) {
		return roleDao.getRoleMembersByRoleId(roleId);
	}

}
