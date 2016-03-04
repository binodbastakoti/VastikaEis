package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Permissions;
import com.vastikaEIS.domain.RolePermissions;
import com.vastikaEIS.domain.Roles;

public interface RoleService {

	long addRoles(Roles roles);

	Roles getRoleById(long id);

	public List<Roles> listAll();

	boolean deleteRole(long id);

	public List<Permissions> listAllPermissions();

	long assignRolePermissions(List<Long> permissions, long roleId);

	List<RolePermissions> getRoleMembersByRoleId(long id);

}
