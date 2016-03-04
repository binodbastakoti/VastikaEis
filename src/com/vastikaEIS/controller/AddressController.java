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

import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.response.AddressJsonData;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.JsonResponse;
import com.vastikaEIS.service.AddressService;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.SecurityUtil;

@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/users/{id}/address", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap addAddress(@Valid @RequestBody Address address, @PathVariable long id, BindingResult result) throws ParseException {
		JsonMap jsonMap = new JsonMap();
		User user = userService.findUserById(id);
		if (user == null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("userNotExist");
			return jsonMap;
		}
		address.setUser(user);
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailure");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}

		boolean addressAdded = addressService.addAddress(address);
		if (addressAdded) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("addressAddedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setSuccessStatus();
			return jsonMap;
		}

	}

	@RequestMapping(value = "/users/{userId}/address/{addressId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap updateAddress(@Valid @RequestBody Address address, @PathVariable long userId, @PathVariable long addressId, BindingResult result) throws ParseException {
	
		address.setId(addressId);
		
		JsonMap jsonMap = new JsonMap();
		User user = userService.findUserById(userId);
		if (user == null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("userNotExist");
			return jsonMap;
		}
		address.setUser(user);
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailure");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}
		
		boolean addressUpdated = addressService.updateAddress(address);
		if (addressUpdated) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("addressAddedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setSuccessStatus();
			return jsonMap;
		}
	}

	@RequestMapping(value = "/addresses/{addressId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteAddress(@PathVariable long addressId) {
		

		JsonMap jsonMap = new JsonMap();
		long id = addressService.deleteAddress(addressId);
		
		if (id < 0) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("AddressNotExist");
			return jsonMap;
		}else{
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("addressAddedMsg");

			return jsonMap;
		}
		
		
	}

	@RequestMapping(value = "/users/{userId}/address", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<AddressJsonData> listByUserId(@PathVariable long userId) throws ParseException {

		List<Address> addresses = new ArrayList<>();
		List<AddressJsonData> addressJsonDatas = new ArrayList<>();
		addresses = addressService.getAddressByUserId(userId);

		for (Address a : addresses) {
			AddressJsonData addressJsonData = new AddressJsonData();
			addressJsonData.setAddressId(a.getId());
			addressJsonData.setStreet(a.getStreet());
			addressJsonData.setCity(a.getCity());
			addressJsonData.setEndDate(a.getEndDate());
			addressJsonData.setStartDate(a.getStartDate());
			addressJsonData.setState(a.getState());
			addressJsonData.setZipCode(a.getZipCode());
			addressJsonDatas.add(addressJsonData);
		}
		return addressJsonDatas;
	}

}
