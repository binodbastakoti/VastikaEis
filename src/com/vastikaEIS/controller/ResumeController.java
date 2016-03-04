package com.vastikaEIS.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.HeroesResume;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.ResumeJsonData;
import com.vastikaEIS.service.ResumeService;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.DateTimeFormatter;
import com.vastikaEIS.util.SecurityUtil;
import com.vastikaEIS.util.StringManipulation;

//import com.vastikaEIS.dto.request.HeroesResumeDto;

@RestController
@RequestMapping("/api")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/heroes/{id}/resumes", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap insertResume(@Valid @RequestBody HeroesResume heroesResume, @PathVariable long id, BindingResult result) throws ParseException {
		long resumeId = resumeService.addResume(heroesResume, id);
		JsonMap jsonMap = new JsonMap();
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailureStatus");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}
		if (resumeId > 0) {
			jsonMap.setSuccessStatus();
			jsonMap.setId(resumeId);
			jsonMap.setMessages("resumeAddedSuccessfully");
			return jsonMap;
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
			return jsonMap;
		}
	}

	@RequestMapping(value = "resumes/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap updateResume(@Valid @RequestBody HeroesResume heroesResume, @PathVariable long id, BindingResult result) throws ParseException {
		JsonMap jsonResponseMap = new JsonMap();

		User applicationUser = securityUtil.getSessionUser();
		HeroesResume resume = resumeService.updateResume(heroesResume, id, applicationUser);

		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailureStatus");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}

		if (resume != null) {
			jsonResponseMap.setSuccessStatus();
			jsonResponseMap.setMessages("resumeUpdatedSuccessfully");
			return jsonResponseMap;
		} else {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("failureMsg");
			return jsonResponseMap;
		}
	}

	@RequestMapping(value = "resumes/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteResume(@PathVariable long id) throws ParseException {
		JsonMap jsonResponseMap = new JsonMap();
		HeroesResume resume = resumeService.deleteResume(id);
		if (resume != null) {
			jsonResponseMap.setSuccessStatus();
			jsonResponseMap.setMessages("resumeDeletedSuccessfully");
			return jsonResponseMap;
		} else {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("failureMsg");
			return jsonResponseMap;
		}
	}

	@RequestMapping(value = "/resumes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<HeroesResume> ListResumes() throws ParseException {
		return resumeService.getAllResume();
	}

	@RequestMapping(value = "/heroes/{heroesId}/resumes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ResumeJsonData> ListUserResumes(@PathVariable long heroesId) throws ParseException {
		List<ResumeJsonData> resumeJsonDatas = new ArrayList<ResumeJsonData>();
		List<HeroesResume> heroesResumes = resumeService.getResumeByUserId(heroesId);
		for (HeroesResume heroesResume : heroesResumes) {
			ResumeJsonData jsonData = new ResumeJsonData();
			jsonData.setId(heroesResume.getId());
			jsonData.setTitle(heroesResume.getTitle());
			jsonData.setResumeContact(heroesResume.getResumeContact());
			jsonData.setEmail(heroesResume.getEmail());
			jsonData.setVisibility(heroesResume.getVisibility());			
			String fileName = heroesResume.getFileUpload().getRealFileName();
			String extension = StringManipulation.getFileExtension(fileName);
			jsonData.setFileType(extension);
			jsonData.setUploadedDate(DateTimeFormatter.formatDateToString(heroesResume.getFileUpload().getUploadedDate()));
			resumeJsonDatas.add(jsonData);
		}
		return resumeJsonDatas;
	}

	@RequestMapping(value = "/resumes/{heroResumeId}/download", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadStuff(@PathVariable long heroResumeId, HttpServletResponse response) throws IOException {
		HeroesResume heroesResume = resumeService.getResumeById(heroResumeId);
		String fullPath = heroesResume.getFileUpload().getFilePath();
		File file = new File(fullPath);
		HttpHeaders respHeaders = new HttpHeaders();
		response.setContentType("application/force-download");
		respHeaders.setContentLength(file.length());
		respHeaders.setContentDispositionFormData("attachment", heroesResume.getFileUpload().getRealFileName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}

}
