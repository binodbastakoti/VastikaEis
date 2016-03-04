package com.vastikaEIS.util;

public class RoleUtil {
	
	/*
	 * Highest role means the user's highest designation.
	 * Means ROLE_ADMIN or ROLE_ADMINISTRATOR
	 * This method is created in order to get user's highest authorization
	 * as user might have multiple roles.
	 * Based on the highest role passed, we will get highest permissions 
	 * as high role will have more permissions.
	 */
	public long getUserHighestRoleId(long userId){
		long roleId = 1;
		return roleId;
	}

}
