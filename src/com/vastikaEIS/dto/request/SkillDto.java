package com.vastikaEIS.dto.request;

import java.io.Serializable;

import javax.validation.Valid;

import com.vastikaEIS.domain.Skill;

public class SkillDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Valid
	private Skill skill;
	private Long skillCategoryId;

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Long getSkillCategoryId() {
		return skillCategoryId;
	}

	public void setSkillCategoryId(Long skillCategoryId) {
		this.skillCategoryId = skillCategoryId;
	}

}
