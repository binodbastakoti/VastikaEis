package com.vastikaEIS.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.ListUtils;

import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.CustomUser;
import com.vastikaEIS.dto.request.Marketer;
import com.vastikaEIS.util.StringManipulation;

public class ResumeMarketingCore {

	public Marketer setMarketerInfo(List<User> recruiters, List<ResumeMarketing> resumeMarketings) {
		Marketer marketer = new Marketer();
		List<CustomUser> assignedRecruiter = new ArrayList<CustomUser>();
		List<User> assignedUser = new ArrayList<User>();

		Map<User, Heroes> uniqueRecruiterMap = new LinkedHashMap<User, Heroes>();

		for (ResumeMarketing marketing : resumeMarketings) {
			uniqueRecruiterMap.put(marketing.getRecruiter(), marketing.getHeroes());
		}

		Iterator itr = uniqueRecruiterMap.keySet().iterator();
		while (itr.hasNext()) {
			User user = (User) itr.next();
			CustomUser customUser = new CustomUser();
			List<String> nameList = new ArrayList<String>();
			nameList.add(user.getFirstName());
			nameList.add(user.getMiddleName());
			nameList.add(user.getLastName());
			String fullName = StringManipulation.manipulatedString(nameList, " ");
			customUser.setName(fullName);
			customUser.setId(user.getId());
			assignedRecruiter.add(customUser);
			assignedUser.add(user);
		}
		marketer.setAssignedRecruiter(assignedRecruiter);
		List<CustomUser> notAssignedRecuiter = new ArrayList<CustomUser>();
		List<User> notAssignedUser = ListUtils.subtract(recruiters, assignedUser);
		for (User user : notAssignedUser) {
			CustomUser customUser = new CustomUser();
			List<String> nameList = new ArrayList<String>();
			nameList.add(user.getFirstName());
			nameList.add(user.getMiddleName());
			nameList.add(user.getLastName());
			String fullName = StringManipulation.manipulatedString(nameList, " ");
			customUser.setName(fullName);
			customUser.setId(user.getId());
			notAssignedRecuiter.add(customUser);
		}
		marketer.setNotAssignedRecuiter(notAssignedRecuiter);
		return marketer;
	}
}
