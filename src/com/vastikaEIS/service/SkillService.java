package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;

public interface SkillService {

	public long addSkill(Skill skill, long skillCategoryId);

	public List<Skill> listAll();

	public Skill findSkillByID(long id);

	public SkillCategory findCategoryById(long skillCategoryId);

	public long deleteSkillById(Skill skill);

}
