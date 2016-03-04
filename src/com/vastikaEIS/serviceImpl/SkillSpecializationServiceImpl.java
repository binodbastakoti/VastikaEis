package com.vastikaEIS.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.SkillSpecializationDao;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.SkillSpecialization;
import com.vastikaEIS.service.SkillSpecializationService;

@Service
@Transactional
public class SkillSpecializationServiceImpl implements
		SkillSpecializationService {

	@Autowired
	private SkillSpecializationDao skillSpecializationDao;

	@Override
	public boolean addSkillSpecialization(
			SkillSpecialization skillSpecialization, SkillCategory skillCategory) {

		return skillSpecializationDao.addSkillSpecialization(skillSpecialization, skillCategory);

	}

}
