package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.core.ResumeMarketingCore;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.CustomUser;
import com.vastikaEIS.dto.request.Marketer;
import com.vastikaEIS.dto.request.ResumeMarketingDto;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.service.ResumeMarketingService;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.StringManipulation;

@RestController
@RequestMapping("/api/marketer")
public class ResumeMarketingController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResumeMarketingService marketingService;

	@RequestMapping(value = "/{heroId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Marketer getMarketers(@PathVariable long heroId) throws ParseException {
		ResumeMarketingCore marketingCore = new ResumeMarketingCore();
		List<User> recruiters = userService.getRecruiter(heroId);
		List<ResumeMarketing> resumeMarketings = marketingService.getAssignedRecruiter(heroId);
		Marketer marketer = marketingCore.setMarketerInfo(recruiters, resumeMarketings);
		return marketer;
	}

	@RequestMapping(value = "/assignHero", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap assignHero(@RequestBody ResumeMarketingDto marketingDto) {
		JsonMap jsonMap = new JsonMap();
		if (marketingService.assignHero(marketingDto)) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("heroAssignMsg");
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
		}
		return jsonMap;
	}

	@RequestMapping(value = "/removeHero/{heroId}/{recruiterId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap removeHero(@PathVariable long heroId,@PathVariable long recruiterId) {
		JsonMap jsonMap = new JsonMap();
		if (marketingService.removeHero(heroId,recruiterId)) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("heroRemoveMsg");
		} else {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
		}
		return jsonMap;
	}
}
