package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.core.HeroCore;
import com.vastikaEIS.core.UserCore;
import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.RoleMembers;
import com.vastikaEIS.domain.Roles;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.HeroesDto;
import com.vastikaEIS.dto.request.UsersDto;
import com.vastikaEIS.dto.response.HeroFullNameJsonData;
import com.vastikaEIS.dto.response.HeroJsonData;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.RoleJson;
import com.vastikaEIS.dto.response.UserJsonData;
import com.vastikaEIS.service.AddressService;
import com.vastikaEIS.service.ContactService;
import com.vastikaEIS.service.DocumentsService;
import com.vastikaEIS.service.RoleService;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.DateTimeFormatter;
import com.vastikaEIS.util.SecurityUtil;
import com.vastikaEIS.util.StringManipulation;

/**
 *
 * @author Devil
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DocumentsService documentService;
	@Autowired
	private SecurityUtil securityUtil;
	@Autowired
	private ContactService contactService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/users/currentUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public JsonMap currentUser() {
		UserCore userCore = new UserCore();
		JsonMap jsonMap = userCore
				.setCurrentUserJson(securityUtil, roleService);
		return jsonMap;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap registerUser(@Valid @RequestBody UsersDto usersDto,
			BindingResult result) throws ParseException, InterruptedException {
		Thread.sleep(2000);
		JsonMap jsonMap = new JsonMap();
		User applicationUser = securityUtil.getSessionUser();
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailure");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}
		User user = userService.findUserByUsername(usersDto.getUser()
				.getEmail());
		if (user != null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("emailAlreadyExistMsg");
			return jsonMap;
		}

		Long userId = userService.addUser(usersDto, applicationUser);

		if (userId > 0) {
			jsonMap.setId(userId);
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("usersAddedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setSuccessStatus();
			return jsonMap;
		}

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap updateUser(@PathVariable long id,
			@Valid @RequestBody UsersDto usersDto, BindingResult result)
			throws ParseException {

		JsonMap jsonMap = new JsonMap();
		User user = userService.findUserById(id);
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailureStatus");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}
		if (user == null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("userDoesnotExist");
			return jsonMap;
		}

		User applicationUser = securityUtil.getSessionUser();
		User existingUserByEmail = userService.findOtherUserByUsername(usersDto
				.getUser().getEmail(), id);

		if (existingUserByEmail != null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("usernameAlreadyExistMsg");
			return jsonMap;
		}

		Long userId = userService.updateUser(usersDto, applicationUser, user);

		if (userId > 0) {
			jsonMap.setId(userId);
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("usersUpdatedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setSuccessStatus();
			return jsonMap;
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserJsonData> listAll() throws ParseException {
		List<User> users = userService.getAllUsers();
		List<UserJsonData> userJsonDatas = new ArrayList<>();
		for (User u : users) {
			userJsonDatas.add(extractUserInfo(u));
		}
		return userJsonDatas;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public UserJsonData findUserById(@PathVariable long id)
			throws ParseException {
		User user = userService.findUserById(id);
		return extractUserInfo(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteUser(@PathVariable long id) throws ParseException {
		JsonMap jsonMap = new JsonMap();
		if (userService.deleteUser(id)) {
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("usersDeletedMsg");

		} else {
			jsonMap.setMessages("userDeleteFailed");
			jsonMap.setSuccessStatus();
		}
		return jsonMap;
	}

	@RequestMapping(value = "/users/checkIfEmailExist", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap findUserByUsername(@RequestBody User user)
			throws ParseException {
		JsonMap jsonMap = new JsonMap();

		User userFromDb;
		if (user.getId() <= 0) {
			userFromDb = userService.findUserByUsername(user.getEmail());

		} else {
			userFromDb = userService.findOtherUserByUsername(user.getEmail(),
					user.getId());
		}

		if (userFromDb != null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("usernameAlreadyExist");
			return jsonMap;
		} else {
			jsonMap.setSuccessStatus();
			return jsonMap;
		}
	}

	@RequestMapping(value = "/heroes", method = RequestMethod.POST, headers = "Accept=application/json")
	public JsonMap registerHero(@Valid @RequestBody HeroesDto heroesDto,
			BindingResult result) throws ParseException, InterruptedException {
		JsonMap jsonMap = new JsonMap();
		Thread.sleep(2000);
		User applicationUser = securityUtil.getSessionUser();
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailure");
			jsonMap.setMessages("validationFailure");

			return jsonMap;
		}
		User user = userService.findUserByUsername(heroesDto.getUser()
				.getEmail());
		if (user != null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("emailAlreadyExistMsg");
			return jsonMap;
		}
		Long userId = userService.addHero(heroesDto, applicationUser);
		if (userId > 0) {
			jsonMap.setId(userId);
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("heroesAddedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setFailedStatus();
			return jsonMap;
		}

	}

	@RequestMapping(value = "/heroes/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap updateUser(@PathVariable long id,
			@Valid @RequestBody HeroesDto heroesDto, BindingResult result)
			throws ParseException {

		JsonMap jsonMap = new JsonMap();
		User user = userService.findUserById(id);
		if (result.hasErrors()) {
			jsonMap.setValidationFailedStatus("validationFailureStatus");
			jsonMap.setMessages("validationFailure");
			return jsonMap;
		}
		if (user == null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("userDoesnotExist");
			return jsonMap;
		}

		User applicationUser = securityUtil.getSessionUser();
		User existingUserByEmail = userService.findOtherUserByUsername(
				heroesDto.getUser().getEmail(), id);

		if (existingUserByEmail != null) {
			jsonMap.setFailedStatus();
			jsonMap.setMessages("usernameAlreadyExistMsg");
			return jsonMap;
		}

		Long userId = userService.updateHero(heroesDto, id, applicationUser);

		if (userId > 0) {
			jsonMap.setId(userId);
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("usersUpdatedMsg");

			return jsonMap;
		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setSuccessStatus();
			return jsonMap;
		}
	}


	@RequestMapping(value = "/heroes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<HeroJsonData> listAllHeroes() throws ParseException {

		List<User> users = userService.getAllHeroes();
		List<HeroJsonData> heroJsonDatas = new ArrayList<>();

		HeroCore heroCore = new HeroCore();
		heroJsonDatas = heroCore.setHeroJsonData(users);
		return heroJsonDatas;
	}

	@RequestMapping(value = "/myHeroes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<HeroJsonData> getAllAssignedHeroesToCurrentUser()
			throws ParseException {
		List<User> users = new ArrayList<User>();
		User applicationUser = securityUtil.getSessionUser();
		List<Heroes> Heroes = userService.getAllAssignedHeroes(applicationUser.getId());
		for (Heroes heroes : Heroes) {
			users.add(heroes.getUser());
		}
		List<HeroJsonData> heroJsonDatas = new ArrayList<>();
		HeroCore heroCore = new HeroCore();
		heroJsonDatas = heroCore.setHeroJsonData(users);
		return heroJsonDatas;
	}

	@RequestMapping(value = "/heroes/names", method = RequestMethod.GET)
	public List<HeroFullNameJsonData> getAllHerosFullName()
			throws ParseException {
		List<User> users = userService.getAllHeroes();
		List<HeroFullNameJsonData> heroJsonDatas = new ArrayList<HeroFullNameJsonData>();

		for (User u : users) {
			List<String> nameList = new ArrayList<String>();
			HeroFullNameJsonData data = new HeroFullNameJsonData();
			nameList.add(u.getFirstName());
			nameList.add(u.getMiddleName());
			nameList.add(u.getLastName());

			String fullName = StringManipulation.manipulatedString(nameList,
					" ");
			data.setFullName(fullName);
			data.setId(u.getId());
			heroJsonDatas.add(data);
		}

		return heroJsonDatas;
	}

	@RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public HeroJsonData getHeroById(@PathVariable long id)
			throws ParseException {
		User user = userService.findActiveHeroById(id);
		HeroCore heroCore = new HeroCore();
		HeroJsonData heroJsonData = heroCore.setHeroJsonData(user);
		return heroJsonData;
	}

	@RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteHero(@PathVariable long id) throws ParseException {
		JsonMap jsonMap = new JsonMap();
		long heroId = userService.deleteHero(id);
		if (heroId > 0) {
			jsonMap.setId(heroId);
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("heroesDeletedMsg");
			return jsonMap;

		} else {
			jsonMap.setMessages("failureMsg");
			jsonMap.setFailedStatus();
			return jsonMap;
		}
	}

	public UserJsonData extractUserInfo(User u) {

		UserJsonData userJsonData = new UserJsonData();
		Address address = addressService.getRecentAddress(u);
		// List<Roles> roles =
		// roleService.getRolesByUserId(u.getId());

		// List<String> fullAddress
		// = new
		// ArrayList<String>();
		// fullAddress.add(address.getCity());
		// fullAddress.add(address.getState());
		//
		// String fulluserAddress =
		// StringManipulation.manipulatedString(fullAddress,
		// ", ");
		// String fullName =
		// StringManipulation.manipulatedString(new
		// ArrayList<>(Arrays.asList(u.getFirstName(),
		// u.getMiddleName(),
		// u.getLastName())), " ");

		userJsonData.setUserId(u.getId());
		userJsonData.setUserName(u.getUsername());
		userJsonData.setFirstName(u.getFirstName());
		userJsonData.setMiddleName(u.getMiddleName());
		userJsonData.setLastName(u.getLastName());
		userJsonData.setContact(contactService.getContactById(u.getId()).get(0)
				.getContactNumber());
		userJsonData.setAddressLine1(address.getAddressLine1());
		userJsonData.setCity(address.getCity());
		userJsonData.setStreet(address.getStreet());
		userJsonData.setState(address.getState());
		userJsonData.setZipCode(address.getZipCode());
		userJsonData.setEmail(u.getEmail());
		userJsonData.setGender(u.getGender());
		userJsonData.setDob(DateTimeFormatter.formatDateToString(u.getDob()));

		List<RoleMembers> roleMembers = u.getRoleMembers();
		List<Roles> roles = new ArrayList<Roles>();
		for (RoleMembers members : roleMembers) {
			roles.add(members.getRoles());
		}

		List<RoleJson> roleJsons = new ArrayList<RoleJson>();
		for (Roles roles2 : roles) {
			RoleJson roleJson = new RoleJson();
			roleJson.setId(roles2.getId());
			roleJson.setName(roles2.getName());
			roleJsons.add(roleJson);
		}
		userJsonData.setRoles(roleJsons);
		// userJsonData.setRegistrationDate(new
		// Date());
		// userJsonData.setUserType(u.getUserType());
		// userJsonData.setStatus(u.isEnabled());

		return userJsonData;
	}

}
