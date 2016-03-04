package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.dto.request.SkillDto;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.SkillJson;
import com.vastikaEIS.service.SkillService;

@RestController
@RequestMapping("/api")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/skills", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap addSkill(@Valid @RequestBody SkillDto skillDto, BindingResult result) throws ParseException {

		Long skillCategoryId = skillDto.getSkillCategoryId();

		Skill skill = skillDto.getSkill();
		JsonMap jsonResponseMap = new JsonMap();

		if (skillCategoryId == null || skillCategoryId <= 0) {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");

			return jsonResponseMap;
		}

		long id = skillService.addSkill(skill, skillCategoryId);

		if (id > 0) {
			jsonResponseMap.setId(id);
			jsonResponseMap.setMessages("skillSuccess");
			jsonResponseMap.setSuccessStatus();
		} else {
			jsonResponseMap.setMessages("skillAddFail");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;

	}

	@RequestMapping(value = "/skills", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SkillJson> listAll() throws ParseException {
		List<Skill> skills = skillService.listAll();
		List<SkillJson> jsonSkills = new ArrayList<>();

		for (Skill s : skills) {
			SkillJson skilljson = new SkillJson();
			skilljson.setSkillId(s.getId());
			skilljson.setSkillName(s.getSkillName());
			skilljson.setSkillCategory(s.getSkillCategory().getCategoryName());
			jsonSkills.add(skilljson);
		}
		return jsonSkills;
	}

	@RequestMapping(value = "/skills/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public SkillJson findSkillByID(@PathVariable long id) throws ParseException {
		Skill skill = skillService.findSkillByID(id);
		SkillJson skilljson = new SkillJson();
		skilljson.setSkillId(skill.getId());
		skilljson.setSkillName(skill.getSkillName());
		skilljson.setSkillCategory(skill.getSkillCategory().getCategoryName());
		return skilljson;
	}

	@RequestMapping(value = "/skills/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap editById(@PathVariable long id, @Valid @RequestBody SkillDto skillDto, BindingResult result) throws ParseException {

		Long skillCategoryId = skillDto.getSkillCategoryId();
		Skill skill = skillDto.getSkill();
		JsonMap jsonResponseMap = new JsonMap();

		if (skillCategoryId == null || skillCategoryId <= 0) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		long skillId = skillService.addSkill(skill, skillCategoryId);

		if (skillId > 0) {
			jsonResponseMap.setMessages("skillUpdateSuccess");
			jsonResponseMap.setSuccessStatus();
		} else {
			jsonResponseMap.setMessages("skillUpdateFail");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;

	}

	@RequestMapping(value = "/skills/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteSkillById(@PathVariable long id) throws ParseException {

		Skill skill = skillService.findSkillByID(id);
		JsonMap jsonResponseMap = new JsonMap();

		if (skill == null) {
			jsonResponseMap.setMessages("skillNotFound");
			jsonResponseMap.setFailedStatus();
		} else {
			long skillId = skillService.deleteSkillById(skill);
			if (skillId > 0) {
				jsonResponseMap.setMessages("skillDeleteSuccess");
				jsonResponseMap.setSuccessStatus();
			} else {
				jsonResponseMap.setMessages("skillDeleteFail");
				jsonResponseMap.setFailedStatus();
			}
		}
		return jsonResponseMap;
	}

}
