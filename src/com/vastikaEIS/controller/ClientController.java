package com.vastikaEIS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean saveClient() {
		return clientService.saveClient();
	}
}
