package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.SkillCategoryJson;
import com.vastikaEIS.dto.response.SkillJson;
import com.vastikaEIS.service.SkillCategoryService;
import com.vastikaEIS.util.MessageProperties;

@RestController
@RequestMapping("/api")
public class SkillCategoryController {
	@Autowired
	private SkillCategoryService skillCategoryService;

	@RequestMapping(value = "/skillCategories", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap addSkillCategory(@Valid @RequestBody SkillCategory skillCategory, BindingResult result) throws ParseException, InterruptedException {

		Thread.sleep(2000);
		JsonMap jsonResponseMap = new JsonMap();

		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		long id = skillCategoryService.addSkillCategory(skillCategory);
		if (id > 0) {
			jsonResponseMap.setId(id);
			jsonResponseMap.setMessages("skillCategorySuccess");
			jsonResponseMap.setStatus("successStatus");

		} else {
			jsonResponseMap.setMessages("skillCategoryAddFail");
			jsonResponseMap.setStatus("failureStatus");
		}

		return jsonResponseMap;
	}

	@RequestMapping(value = "/skillCategories", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SkillCategoryJson> listAll() throws ParseException {
		List<SkillCategory> skillCategories = skillCategoryService.listAll();
		List<SkillCategoryJson> jsonCategories = new ArrayList<>();

		for (SkillCategory sc : skillCategories) {
			SkillCategoryJson skillCategoryJson = new SkillCategoryJson();
			skillCategoryJson.setCategoryId(sc.getId());
			skillCategoryJson.setCategoryName(sc.getCategoryName());
			skillCategoryJson.setCategoryDesc(sc.getCategoryDesc());

			jsonCategories.add(skillCategoryJson);
		}

		return jsonCategories;

	}

	@RequestMapping(value = "/skillCategories/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public SkillCategoryJson getById(@PathVariable long id) throws ParseException {

		SkillCategory sc = skillCategoryService.getCategoryById(id);
		SkillCategoryJson skillCategoryJson = new SkillCategoryJson();
		skillCategoryJson.setCategoryId(sc.getId());
		skillCategoryJson.setCategoryName(sc.getCategoryName());
		skillCategoryJson.setCategoryDesc(sc.getCategoryDesc());

		return skillCategoryJson;

	}

	@RequestMapping(value = "/skillCategories/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap editById(@PathVariable long id, @Valid @RequestBody SkillCategory skillCategory, BindingResult result) throws ParseException {

		SkillCategory sc = skillCategoryService.getCategoryById(id);
		JsonMap jsonResponseMap = new JsonMap();
		MessageProperties msgProperty = new MessageProperties();
		Properties prop = msgProperty.getPropValues();

		if (sc == null) {
			jsonResponseMap.setStatus("failureStatus");
			jsonResponseMap.setMessages("skillCategoryNotFound");
			return jsonResponseMap;
		}

		skillCategory.setId(id);

		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		long catId = skillCategoryService.addSkillCategory(skillCategory);
		if (catId > 0) {
			jsonResponseMap.setMessages("skillCategoryUpdateSuccess");
			jsonResponseMap.setStatus("successStatus");
		} else {
			jsonResponseMap.setMessages("skillCategoryUpdateFail");
			jsonResponseMap.setStatus("failureStatus");
		}

		return jsonResponseMap;
	}

	@RequestMapping(value = "/skillCategories/{id}/skills", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SkillJson> getAllSkillsByCatId(@PathVariable long id) throws ParseException {

		List<Skill> skills = skillCategoryService.getAllSkillsByCatId(id);
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

	@RequestMapping(value = "/skillCategories/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteSkillCategoryById(@PathVariable long id) throws ParseException {

		SkillCategory sc = skillCategoryService.getCategoryById(id);
		JsonMap jsonResponseMap = new JsonMap();

		if (sc == null) {
			jsonResponseMap.setStatus("failureStatus");
			jsonResponseMap.setMessages("skillCategoryNotFound");
			return jsonResponseMap;
		}

		long catId = skillCategoryService.deleteSkillCategory(id);
		if (catId > 0) {
			jsonResponseMap.setMessages("skillCategoryDeleteSuccess");
			jsonResponseMap.setStatus("successStatus");
		} else {
			jsonResponseMap.setMessages("skillCategoryDeleteFail");
			jsonResponseMap.setStatus("failureStatus");
		}

		return jsonResponseMap;

	}
}
