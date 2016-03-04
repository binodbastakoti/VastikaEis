package com.vastikaEIS.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.HeroesSkills;

@Repository
@Transactional
public class UserSkillsDao {
	
	@Autowired
	SessionFactory sf;
	
	public boolean addUserSkill(HeroesSkills userSkill){
		long id = (long) sf.getCurrentSession().save(userSkill);
		if(id>0){
			return true;
		}
		return false;
	}
	
	public void updateUserSkill(HeroesSkills userSkill){
		sf.getCurrentSession().merge(userSkill);
	}
}
