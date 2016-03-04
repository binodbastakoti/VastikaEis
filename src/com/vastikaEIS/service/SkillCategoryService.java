package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;

public interface SkillCategoryService {

	long addSkillCategory(SkillCategory skillCategory);

	public SkillCategory getCategoryById(long id);

	public List<SkillCategory> listAll();

	List<Skill> getAllSkillsByCatId(long id);

	long deleteSkillCategory(long id);

}
