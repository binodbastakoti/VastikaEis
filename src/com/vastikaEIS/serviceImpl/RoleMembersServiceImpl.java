package com.vastikaEIS.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.dao.RoleMembersDao;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.RoleMembersService;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class RoleMembersServiceImpl implements RoleMembersService {

	@Autowired
	RoleMembersDao roleMembersDao;

	@Override
	public void deleteRoleMembersByMember(User userFromDb) {
		// TODO Auto-generated method stub
		roleMembersDao.deleteRoleMembersByMember(userFromDb);
	}

}
