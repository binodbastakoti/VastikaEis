package com.vastikaEIS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.dao.SkillDao;
import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.service.SkillService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillDao skillDao;

	public long addSkill(Skill skill, long skillCategoryId) {
		skill.setStatus(Constant.ACTIV);
		SkillCategory category = skillDao.findCategoryById(skillCategoryId);
		if (category != null) {
			skill.setSkillCategory(category);
			return skillDao.addSkill(skill);
		} else {
			return 0;
		}
	}

	@Override
	public Skill findSkillByID(long id) {
		return skillDao.findSkillByID(id);
	}

	@Override
	public List<Skill> listAll() {
		return skillDao.listAll();
	}

	@Override
	public SkillCategory findCategoryById(long skillCategoryId) {
		return skillDao.findCategoryById(skillCategoryId);
	}

	@Override
	public long deleteSkillById(Skill skill) {
		skill.setStatus(Constant.DELETED);
		return skillDao.addSkill(skill);
	}

}
