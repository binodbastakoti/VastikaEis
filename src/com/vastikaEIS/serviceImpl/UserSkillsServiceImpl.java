package com.vastikaEIS.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.UserSkillsDao;
import com.vastikaEIS.domain.HeroesSkills;
import com.vastikaEIS.service.UserSkillsService;

@Service
@Transactional
public class UserSkillsServiceImpl implements UserSkillsService {
	@Autowired
	private UserSkillsDao userSkillsDao;

	public boolean addUserSkills(HeroesSkills userSkill) {
		return userSkillsDao.addUserSkill(userSkill);
	}
 
	public void updateUserSkills(HeroesSkills userSkill) {
		userSkillsDao.updateUserSkill(userSkill);
		
	}
}