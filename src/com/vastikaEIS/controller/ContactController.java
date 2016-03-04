package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.response.ContactJsonData;
import com.vastikaEIS.dto.response.JsonResponse;
import com.vastikaEIS.service.ContactService;
import com.vastikaEIS.util.SecurityUtil;

@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addContact(Contacts contact) {
		contactService.addContact(contact);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("Contact added successfully");
		return "success";
	}

	@RequestMapping(value = "/update/{contactId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonResponse updateContact(@PathVariable long contactId, Contacts contact) {
		User currentUser = securityUtil.getSessionUser();
		contact.setId(contactId);
		// contact.setUser(userService.findUserById(currentUser.getId()));
		contact.setUser(currentUser);
		contactService.updateContact(contact, contactId, currentUser);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("Contact updated successfully");
		return jsonResponse;
	}

	@RequestMapping(value = "/users/{id}/contact", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ContactJsonData> listById(@PathVariable long id) throws ParseException {

		List<Contacts> contact = new ArrayList<>();
		List<ContactJsonData> contactJsonDatas = new ArrayList<>();
		contact = contactService.getContactById(id);

		for (Contacts c : contact) {
			ContactJsonData contactJsonData = new ContactJsonData();
			contactJsonData.setId(c.getId());
			contactJsonData.setContactNumber(c.getContactNumber());
			contactJsonDatas.add(contactJsonData);
		}

		return contactJsonDatas;

	}

	@RequestMapping(value = "/users/{id}/recentDate", method = RequestMethod.GET, headers = "Accept=application/json")
	public ContactJsonData listByRecentDate(@PathVariable long id) throws ParseException {

		Contacts contact = contactService.getRecentContact(id);

		ContactJsonData contactJsonData = new ContactJsonData();
		contactJsonData.setId(contact.getId());
		contactJsonData.setContactNumber(contact.getContactNumber());

		return contactJsonData;

	}

	@RequestMapping(value = "/getAllContacts", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ContactJsonData> listAll() throws ParseException {

		List<Contacts> contact = new ArrayList<>();
		List<ContactJsonData> contactJsonDatas = new ArrayList<>();
		contact = contactService.getAllContacts();

		for (Contacts c : contact) {
			ContactJsonData contactJsonData = new ContactJsonData();
			contactJsonData.setId(c.getId());
			contactJsonData.setContactNumber(c.getContactNumber());
			contactJsonDatas.add(contactJsonData);
		}
		return contactJsonDatas;
	}
}
