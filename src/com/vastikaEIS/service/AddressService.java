package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.User;

public interface AddressService {

	public boolean addAddress(Address addr);
	
	public List<Address> getAddressByUserId(long userId);

	public boolean updateAddress(Address address);
	
	public long deleteAddress(long addressId);
	
	public Address getRecentAddress(User user);
}
