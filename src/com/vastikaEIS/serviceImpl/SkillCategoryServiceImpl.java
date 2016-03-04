package com.vastikaEIS.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.dao.SkillCategoryDao;
import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.service.SkillCategoryService;

@Service
@Transactional
public class SkillCategoryServiceImpl implements SkillCategoryService {

	@Autowired
	private SkillCategoryDao skillCategoryDao;

	public long addSkillCategory(SkillCategory skillCategory) {
		skillCategory.setStatus(Constant.ACTIV);
		return skillCategoryDao.addSkillCategory(skillCategory);
	}

	@Override
	public SkillCategory getCategoryById(long id) {
		return skillCategoryDao.getSkillCategoryById(id);
	}

	@Override
	public List<SkillCategory> listAll() {
		return skillCategoryDao.listAll();
	}

	@Override
	public List<Skill> getAllSkillsByCatId(long id) {

		return skillCategoryDao.getAllSkillsByCatId(id);
	}

	@Override
	public long deleteSkillCategory(long id) {
		SkillCategory skillCategory = skillCategoryDao.getSkillCategoryById(id);
		skillCategory.setStatus(Constant.DELETED);
		return skillCategoryDao.addSkillCategory(skillCategory);
	}

}
