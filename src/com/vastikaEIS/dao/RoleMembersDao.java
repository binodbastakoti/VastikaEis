package com.vastikaEIS.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.User;

@Repository
@Transactional
public class RoleMembersDao {

	@Autowired
	SessionFactory sf;
	
	public void deleteRoleMembersByMemberId(long userId) {
		
		
	}

	public void deleteRoleMembersByMember(User userFromDb) {
		try {
			Query query = sf.getCurrentSession().createQuery("Delete RoleMembers rm where rm.user=:userFromDb");
			query.setParameter("userFromDb", userFromDb);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	
}
