package com.vastikaEIS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.SkillSpecialization;
import com.vastikaEIS.dto.response.JsonResponse;
import com.vastikaEIS.service.SkillSpecializationService;

@RestController
public class SkillSpecializationCategory {

	@Autowired
	private SkillSpecializationService skillSpecializationService;

	@RequestMapping(value = "addSkillSpecialization")
	public JsonResponse addSkillSpecialization(SkillSpecialization skillSpecialization, SkillCategory skillCategory) {
		skillSpecializationService.addSkillSpecialization(skillSpecialization, skillCategory);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("Skill Specialization added succesfully");
		return jsonResponse;
	}
}
