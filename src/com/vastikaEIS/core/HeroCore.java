package com.vastikaEIS.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.HeroesSkills;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.ResumeMarketingDto;
import com.vastikaEIS.dto.response.HeroJsonData;
import com.vastikaEIS.dto.response.SkillDto;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.DateTimeFormatter;

public class HeroCore {
	private UserService userService;

	public List<HeroJsonData> setHeroJsonData(List<User> users) {
		List<HeroJsonData> heroJsonDatas = new ArrayList<>();

		for (User user : users) {
			if (user.getUserType() == Constant.HERO) {
				HeroJsonData heroJsonData = setUserPersonalInfo(user);
				
				// For list of skills
				List<SkillDto> skillDtos = new ArrayList<SkillDto>();
				List<HeroesSkills> heroSKills = user.getHeroes().getHeroesSkills();
				for (HeroesSkills heroesSkills : heroSKills) {
					SkillDto skillDto = new SkillDto();
					skillDto.setSkillId(heroesSkills.getSkill().getId());
					skillDto.setSkillName(heroesSkills.getSkill().getSkillName());
					skillDto.setYearsOfExperience(heroesSkills.getYearsOfExperience());
					skillDtos.add(skillDto);
				}
				heroJsonData.setSkills(skillDtos);

								
				heroJsonDatas.add(heroJsonData);
			}
		}
		return heroJsonDatas;
	}
	
	public HeroJsonData setHeroJsonData(User user) {
		HeroJsonData heroJsonData = new HeroJsonData();
		if (user.getUserType() == Constant.HERO) {
			heroJsonData = setUserPersonalInfo(user);
			Map<String, Integer> heroSKillsMap = new HashMap<>();
			List<HeroesSkills> heroSKills = user.getHeroes().getHeroesSkills();

			for (HeroesSkills heroesSkills : heroSKills) {
				String skillId = String.valueOf(heroesSkills.getSkill().getId());
				heroSKillsMap.put(skillId, heroesSkills.getYearsOfExperience());
			}
			heroJsonData.setSkillsMap(heroSKillsMap);
			
			
		}
		return heroJsonData;
	}

	public HeroJsonData setUserPersonalInfo(User user) {
		HeroJsonData heroJsonData = new HeroJsonData();
		heroJsonData.setFirstName(user.getFirstName());
		
		heroJsonData.setLastName(user.getLastName());
		heroJsonData.setMiddleName(user.getMiddleName());
		heroJsonData.setDob(DateTimeFormatter.formatDateToString(user.getDob()));
		heroJsonData.setGender(user.getGender());
		heroJsonData.setEmail(user.getEmail());

		Heroes heroes = user.getHeroes();
		heroJsonData.setAvailability(heroes.getAvailability());
		heroJsonData.setStatus(heroes.getStatus());
		heroJsonData.setPrimeOK(heroes.getPrimeOK());
		heroJsonData.setVisaStatus(heroes.getVisaStatus());
		heroJsonData.setSkillSpecialization(heroes.getSkillSpecialization());
		heroJsonData.setTitle(user.getHeroes().getSkillSpecialization());
		for (Address address : user.getUserAddresses()) {
			if (address.getEndDate() == null) {
				heroJsonData.setAddressLine1(address.getAddressLine1());
				heroJsonData.setStreet(address.getStreet());
				heroJsonData.setCity(address.getCity());
				heroJsonData.setState(address.getState());
				heroJsonData.setZipCode(address.getZipCode());
			}
		}
		heroJsonData.setContactNumber(user.getContacts().get(0).getContactNumber());

		SkillCategory skillCategory = user.getHeroes().getSkillCategory();
		heroJsonData.setSkillCategoryId(skillCategory.getId());
		heroJsonData.setUserId(user.getId());
		heroJsonData.setUserName(user.getUsername());
		heroJsonData.setSkillCategoryName((skillCategory.getCategoryName()));
		
		return heroJsonData;
	}
}
