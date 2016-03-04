package com.vastikaEIS.dto.response;

import java.util.List;

public class UserJsonData {

	private long userId;
	private String userName;
	private String email;
	private String contact;
	private String dob;
	private Character gender;
	private String firstName;
	private String lastName;
	private String MiddleName;
	private String addressLine1;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private List<RoleJson> roles;

	public String getDob() {
		return dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public List<RoleJson> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleJson> roles) {
		this.roles = roles;
	}

}
