package com.vastikaEIS.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HeroJsonData implements Serializable {
	private long userId;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private char gender;
	private String email;
	private String addressLine1;
	private String city;
	private String state;
	private String street;
	private String zipCode;
	private String contactNumber;
	private String skillCategoryName;
	private String title;
	private String status;
	private String visaStatus;
	private Character primeOK;
	private String availability;
	private String skillSpecialization;
	private long skillCategoryId;
	private Map<String, Integer> skillsMap;
	private List<SkillDto> skills;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSkillCategoryName() {
		return skillCategoryName;
	}

	public void setSkillCategoryName(String skillCategoryName) {
		this.skillCategoryName = skillCategoryName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public String getAvailability() {
		return availability;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Map<String, Integer> getSkillsMap() {
		return skillsMap;
	}

	public void setSkillsMap(Map<String, Integer> skillsMap) {
		this.skillsMap = skillsMap;
	}

	public String getSkillSpecialization() {
		return skillSpecialization;
	}

	public void setSkillSpecialization(String skillSpecialization) {
		this.skillSpecialization = skillSpecialization;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getSkillCategoryId() {
		return skillCategoryId;
	}

	public void setSkillCategoryId(long skillCategoryId) {
		this.skillCategoryId = skillCategoryId;
	}

	public List<SkillDto> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDto> skills) {
		this.skills = skills;
	}

	public Character getPrimeOK() {
		return primeOK;
	}

	public void setPrimeOK(Character primeOK) {
		this.primeOK = primeOK;
	}

}
