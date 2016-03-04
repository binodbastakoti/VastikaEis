package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vastikaEIS.domain.Test;

@RestController
public class TestController {
	@RequestMapping(value = "/mycall", method = RequestMethod.POST, headers = "Accept=application/json")
	public String executeSecurity(@RequestBody List<Test> tests) throws ParseException {
		System.out.println("Call----");
		for (Test test : tests) {
			System.out.println("name====" + test.getName());
			System.out.println("age====" + test.getAge());
		}
		return "success";
		// return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView test() throws ParseException {
		System.out.println("username====");
		ModelAndView mav = new ModelAndView("fileUpload");
		return mav;
		// return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
	}
}
