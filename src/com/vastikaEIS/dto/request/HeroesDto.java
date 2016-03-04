package com.vastikaEIS.dto.request;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;

import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.User;

public class HeroesDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Valid
	private User user;
	@Valid
	private Heroes heroes;
	@Valid
	private Address address;
	@Valid
	private Contacts contacts;

	private Long skillCategoryId;
	private Map<String, Integer> skillsMap;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Heroes getHeroes() {
		return heroes;
	}

	public void setHeroes(Heroes heroes) {
		this.heroes = heroes;
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

	public Long getSkillCategoryId() {
		return skillCategoryId;
	}

	public void setSkillCategoryId(Long skillCategoryId) {
		this.skillCategoryId = skillCategoryId;
	}

	public Map<String, Integer> getSkillsMap() {
		return skillsMap;
	}

	public void setSkillsMap(Map<String, Integer> skillsMap) {
		this.skillsMap = skillsMap;
	}

}
