package com.vastikaEIS.service;

import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.SkillSpecialization;

public interface SkillSpecializationService {

	public boolean addSkillSpecialization(SkillSpecialization skillSpecialization, SkillCategory skillCategory);

}
