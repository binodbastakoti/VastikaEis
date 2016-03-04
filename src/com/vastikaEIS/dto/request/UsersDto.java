package com.vastikaEIS.dto.request;

import java.util.List;

import javax.validation.Valid;

import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.User;

public class UsersDto {
	@Valid
	private User user;
	@Valid
	private Address address;
	@Valid
	private Contacts contacts;
	private List<Long> roleIds;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

}
