package com.vastikaEIS.service;

import com.vastikaEIS.domain.User;

public interface RoleMembersService {
	void deleteRoleMembersByMember(User userFromDb);

}
