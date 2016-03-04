package com.vastikaEIS.core;

import java.util.ArrayList;
import java.util.List;

import com.vastikaEIS.domain.Permissions;
import com.vastikaEIS.domain.RoleMembers;
import com.vastikaEIS.domain.RolePermissions;
import com.vastikaEIS.domain.Roles;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.PermissionsJson;
import com.vastikaEIS.service.RoleService;
import com.vastikaEIS.util.SecurityUtil;
import com.vastikaEIS.util.StringManipulation;

public class UserCore {
	public JsonMap setCurrentUserJson(SecurityUtil securityUtil,
			RoleService roleService) {
		JsonMap jsonMap = new JsonMap();
		User currentUser = securityUtil.getSessionUser();
		List<RoleMembers> roleMembers = currentUser.getRoleMembers();
		List<Roles> roles = new ArrayList<>();
		for (RoleMembers rm : roleMembers) {
			Roles role = new Roles();
			role.setId(rm.getRoles().getId());
			role.setName(rm.getRoles().getName());
			roles.add(role);
		}
		List<Permissions> permissions = new ArrayList<Permissions>();
		for (Roles userRoles : roles) {
			List<RolePermissions> rolePermissions = roleService
					.getRoleMembersByRoleId(userRoles.getId());
			if (rolePermissions != null) {
				for (RolePermissions rolePermission : rolePermissions) {
					permissions.add(rolePermission.getPermissions());
				}
			}
		}
		List<String> nameList = new ArrayList<String>();
		nameList.add(currentUser.getFirstName());
		nameList.add(currentUser.getMiddleName());
		nameList.add(currentUser.getLastName());

		String fullName = StringManipulation.manipulatedString(nameList, " ");

		List<String> jsonPermissions = new ArrayList<>();
		for (Permissions p : permissions) {
			jsonPermissions.add(p.getName());
		}
		jsonMap.setPermissionJson(jsonPermissions);
		jsonMap.setId(currentUser.getId());
		jsonMap.setName(fullName);
		jsonMap.setSuccessStatus();
		return jsonMap;
	}
}
