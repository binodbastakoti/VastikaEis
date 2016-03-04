package com.vastikaEIS.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.ClientDao;
import com.vastikaEIS.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao ClientDao;

	@Override
	public boolean saveClient() {
		return ClientDao.saveClient();
	}

}
