package com.vastikaEIS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.dao.AddressDao;
import com.vastikaEIS.dao.UserDao;
import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private UserDao userDao;

	public boolean addAddress(Address addr) {
		return addressDao.addAddress(addr);
	}

	public boolean updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}

	public long deleteAddress(long addressId) {
		return addressDao.deleteAddress(addressId);
	}

	@Override
	public List<Address> getAddressByUserId(long userId) {
		User user = userDao.findUserById(userId);
		return addressDao.getAddressByUserId(user);
	}

	@Override
	public Address getRecentAddress(User user) {
		return addressDao.getRecentAddress(user);
	}
}
