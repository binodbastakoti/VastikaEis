package com.vastikaEIS.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.HeroesSkills;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.RoleMembers;
import com.vastikaEIS.domain.Roles;
import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.HeroesDto;
import com.vastikaEIS.dto.request.UsersDto;
import com.vastikaEIS.service.AddressService;
import com.vastikaEIS.service.ContactService;
import com.vastikaEIS.service.DocumentTypeService;
import com.vastikaEIS.service.DocumentsService;
import com.vastikaEIS.service.RoleMembersService;
import com.vastikaEIS.service.RoleService;
import com.vastikaEIS.service.SkillCategoryService;
import com.vastikaEIS.service.UserSkillsService;
import com.vastikaEIS.serviceImpl.MailService;
import com.vastikaEIS.util.PasswordEncoderGenerator;
import com.vastikaEIS.util.RandomStringGenerator;

/**
 *
 * @author Devil
 */
@Transactional()
@Repository
public class UserDao {

	@Autowired
	private SessionFactory sf;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private UserSkillsService userSkillsService;

	@Autowired
	private DocumentTypeService documentTypeService;

	@Autowired
	private SkillCategoryService skillCategoryService;

	@Autowired
	private MailService mailService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleMembersService roleMembersService;

	public User findUserById(long id) {
		return (User) sf.getCurrentSession().load(User.class, id);
	}

	public boolean doLogin(User user) {
		Query query = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=:username and u.password=:password and u.active=:active");
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());
		query.setParameter("active", user.isEnabled());
		User user1 = (User) query.uniqueResult();
		if (user1 != null) {
			return true;
		}
		return false;
	}

	public long addUser(UsersDto usersDto, User applicationUser) {
		User user = usersDto.getUser();
		Contacts contact = usersDto.getContacts();
		Address address = usersDto.getAddress();
		List<Long> rolesId = usersDto.getRoleIds();
		user = setCommonInfoOfUser(user, applicationUser, Constant.OTHER);

		try {
			//System.out.println("called-------------");
			long userId = (long) sf.getCurrentSession().save(user);
			User currentUser = (User) sf.getCurrentSession().get(User.class,
					userId);

			address.setStartDate(new Date());
			address.setUser(currentUser);

			sf.getCurrentSession().save(address);

			contact.setUser(currentUser);
			sf.getCurrentSession().save(contact);

			for (int i = 0; i < rolesId.size(); i++) {
				System.out.println(rolesId.get(i) + "role Idsss");
				Roles role = roleService.getRoleById(rolesId.get(i));
				RoleMembers roleMembers = new RoleMembers();
				roleMembers.setRoles(role);
				roleMembers.setUser(currentUser);
				sf.getCurrentSession().save(roleMembers);
			}

			return userId;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public boolean deleteUser(long id) {
		try {
			Query query = sf.getCurrentSession().createQuery(
					"update User set enabled=:enabled where id=:id");
			query.setParameter("enabled", Constant.DISABLED);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			if (result > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<User> getAllUsers() {
		Query str = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.enabled=:enabled and u.userType!=:userType");
		str.setParameter("enabled", Constant.ENABLED);
		str.setParameter("userType", Constant.HERO);
		return str.list();
	}

	public User findUserByUsername(String username) {
		Query query = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=:username and u.enabled=:enabled");
		query.setParameter("username", username);
		query.setParameter("enabled", true);
		return (User) query.uniqueResult();
	}

	public User findUserByEamil(String email) {
		Query query = sf.getCurrentSession().createQuery(
				"from User u where u.email=:email and u.enabled=:enabled");
		query.setParameter("email", email);
		query.setParameter("enabled", true);
		return (User) query.uniqueResult();
	}

	public User findOtherUserByUsername(String email, long id) {
		Query query = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.username=:username and u.id!=:id and u.enabled=:enabled");
		query.setParameter("username", email);
		query.setParameter("enabled", true);
		query.setParameter("id", id);
		return (User) query.uniqueResult();
	}

	public long updateHero(HeroesDto heroesDto, long userId,
			User applicationUser) {
		try {
			User userFromDb = (User) sf.getCurrentSession().get(User.class,
					userId);
			Map<String, Integer> heroSkillMap = heroesDto.getSkillsMap();
			User user = heroesDto.getUser();

			updateUserCommonInfo(userFromDb, user, heroesDto.getAddress(),
					heroesDto.getContacts(), applicationUser);

			Heroes heroesFromDB = userFromDb.getHeroes();
			Heroes newHeroes = heroesDto.getHeroes();
			long skillCategoryId = heroesDto.getSkillCategoryId();
			SkillCategory skillCatergory = (SkillCategory) skillCategoryService
					.getCategoryById(skillCategoryId);
			heroesFromDB.setSkillCategory(skillCatergory);
			heroesFromDB.setUser(userFromDb);
			heroesFromDB.setAvailability(newHeroes.getAvailability());
			heroesFromDB.setPrimeOK(newHeroes.getPrimeOK());
			heroesFromDB.setSkillSpecialization(newHeroes
					.getSkillSpecialization());
			heroesFromDB.setStatus(newHeroes.getStatus());
			heroesFromDB.setVisaStatus(newHeroes.getVisaStatus());
			sf.getCurrentSession().merge(heroesFromDB);

			List<HeroesSkills> heroesSkills = heroesFromDB.getHeroesSkills();
			for (HeroesSkills hskill : heroesSkills) {
				sf.getCurrentSession().delete(hskill);
			}

			Iterator it = heroSkillMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				Long skillId = Long.parseLong(pair.getKey().toString());
				Skill skill = (Skill) sf.getCurrentSession().get(Skill.class,
						skillId);
				Integer yrsOfExp = Integer.parseInt(pair.getValue().toString());
				HeroesSkills newHeroSkill = new HeroesSkills();
				newHeroSkill.setHeroes(heroesFromDB);
				newHeroSkill.setSkill(skill);
				newHeroSkill.setYearsOfExperience(yrsOfExp);
				userSkillsService.addUserSkills(newHeroSkill);
			}
			return userFromDb.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private long updateUserCommonInfo(User userFromDb, User user,
			Address changedAddress, Contacts newContacts, User applicationUser) {
		userFromDb.setDob(user.getDob());
		userFromDb.setEmail(user.getEmail());
		userFromDb.setUsername(user.getEmail());
		userFromDb.setFirstName(user.getFirstName());
		userFromDb.setMiddleName(user.getMiddleName());
		userFromDb.setLastName(user.getLastName());
		userFromDb.setGender(user.getGender());
		userFromDb.setModifiedBy(applicationUser);
		userFromDb.setModifiedDate(new Date());

		List<Address> addresses = userFromDb.getUserAddresses();
		Address address = changedAddress;
		for (Address dbaddress : addresses) {
			// Address newAddress = new Address();
			if (addressChanged(dbaddress, address)) {
				// dbaddress.setEndDate(new Date());
				dbaddress.setAddressLine1(address.getAddressLine1());
				dbaddress.setCity(address.getCity());
				dbaddress.setState(address.getState());
				dbaddress.setStreet(address.getStreet());
				dbaddress.setZipCode(address.getZipCode());

				sf.getCurrentSession().merge(dbaddress);
				// newAddress.setStartDate(new Date());
				// newAddress.setAddressLine1(address.getAddressLine1());
				// newAddress.setCity(address.getCity());
				// newAddress.setState(address.getState());
				// newAddress.setStreet(address.getStreet());
				// newAddress.setZipCode(address.getZipCode());
				// sf.getCurrentSession().save(newAddress);
			}
		}

		List<Contacts> contacts = userFromDb.getContacts();
		for (Contacts dbcontact : contacts) {
			if (!dbcontact.getContactNumber().equals(
					newContacts.getContactNumber())) {
				dbcontact.setContactNumber(newContacts.getContactNumber());
				sf.getCurrentSession().merge(dbcontact);
			}
		}

		sf.getCurrentSession().merge(userFromDb);
		return userFromDb.getId();
	}

	private boolean addressChanged(Address dbaddress, Address address) {
		if (!dbaddress.getCity().equals(address.getCity())
				|| !dbaddress.getStreet().equals(address.getStreet())
				|| !dbaddress.getState().equals(address.getState())
				|| !dbaddress.getZipCode().equals(address.getZipCode())) {

			return true;
		}
		return false;
	}

	public long addHero(HeroesDto heroesDto, User applicationUser) {
		User user = heroesDto.getUser();
		Heroes heroes = heroesDto.getHeroes();
		Contacts contact = heroesDto.getContacts();
		Address address = heroesDto.getAddress();
		Long skillCategoryId = heroesDto.getSkillCategoryId();
		Map<String, Integer> heroSkillMap = heroesDto.getSkillsMap();
		user = setCommonInfoOfUser(user, applicationUser, Constant.HERO);
		String randPassword = "";
		try {

			SkillCategory skillCatergory = (SkillCategory) skillCategoryService
					.getCategoryById(skillCategoryId);
			heroes.setSkillCategory(skillCatergory);
			long userId = (long) sf.getCurrentSession().save(user);
			User currentUser = (User) sf.getCurrentSession().get(User.class,
					userId);

			heroes.setUser(currentUser);
			long heroesId = (long) sf.getCurrentSession().save(heroes);

			Heroes currentHero = (Heroes) sf.getCurrentSession().load(
					Heroes.class, heroesId);

			address.setStartDate(new Date());
			address.setUser(currentUser);

			sf.getCurrentSession().save(address);

			contact.setUser(currentUser);
			sf.getCurrentSession().save(contact);

			Iterator it = heroSkillMap.entrySet().iterator();
			while (it.hasNext()) {
				HeroesSkills heroesSkills = new HeroesSkills();

				Map.Entry pair = (Map.Entry) it.next();
				Long skillId = Long.parseLong(pair.getKey().toString());
				Skill skill = (Skill) sf.getCurrentSession().get(Skill.class,
						skillId);
				Integer yrsOfExp = Integer.parseInt(pair.getValue().toString());
				heroesSkills.setHeroes(currentHero);
				heroesSkills.setSkill(skill);
				heroesSkills.setYearsOfExperience(yrsOfExp);
				userSkillsService.addUserSkills(heroesSkills);
			}
			if (sendEmail(currentUser, randPassword)) {
				return userId;
			}

			return userId;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean sendEmail(User user, String password) {
		try {
			String bodyMsg = "Hi " + user.getFirstName() + " " + user.getLastName() + ", "
					+ "Your Username: " + user.getEmail() + " and Password: " + password;
			mailService.sendMail(user.getEmail(), "VastikaEIS Account Credentials",
					bodyMsg);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public User setCommonInfoOfUser(User user, User applicationUser,
			char userType) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		firstName = firstName.substring(0, 1).toUpperCase()
				+ firstName.substring(1).toLowerCase();
		lastName = lastName.substring(0, 1).toUpperCase()
				+ lastName.substring(1).toLowerCase();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(user.getEmail());
		if (userType == Constant.HERO) {
			user.setUserType(Constant.HERO);
		} else if (userType == Constant.RECRUITER) {
			user.setUserType(Constant.RECRUITER);
		} else {
			user.setUserType(Constant.OTHER);
		}

		user.setEnabled(true);
		user.setCreatedDate(new Date());
		user.setCreatedBy(applicationUser);
		String randPassword = "";
		try {
			randPassword = RandomStringGenerator.generateRandomString(5,
					RandomStringGenerator.Mode.ALPHANUMERIC);
			user.setPassword(PasswordEncoderGenerator
					.beCryptPassword(randPassword));
			sendEmail(user, randPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public long updateUser(UsersDto usersDto, User applicationUser,
			User userFromDb) {
		try {
			roleMembersService.deleteRoleMembersByMember(userFromDb);

			long userId = updateUserCommonInfo(userFromDb, usersDto.getUser(),
					usersDto.getAddress(), usersDto.getContacts(),
					applicationUser);
			List<Long> rolesId = usersDto.getRoleIds();

			for (int i = 0; i < rolesId.size(); i++) {

				Roles role = roleService.getRoleById(rolesId.get(i));
				RoleMembers roleMembers = new RoleMembers();

				// if(!(roleMembers.getRoles().equals(role) &&
				// roleMembers.getUser().equals(userFromDb))){
				roleMembers.setRoles(role);
				roleMembers.setUser(userFromDb);
				sf.getCurrentSession().merge(roleMembers);
				// }

			}
			return userId;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<User> getAllHeroes() {

		Query str = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.enabled=:enabled and u.userType=:userType");
		str.setParameter("enabled", Constant.ENABLED);
		str.setParameter("userType", Constant.HERO);
		return str.list();
	}

	public List<Heroes> getAllAssignedHeroes(long recruiterId) {
		Query str = sf
				.getCurrentSession()
				.createQuery(
						"select distinct(rm.heroes) from ResumeMarketing rm where rm.recruiter.id=:recruiterId");
		str.setParameter("recruiterId", recruiterId);
		return str.list();
	}
	
//	public List<> getAllMarketedHeroes(){
//		Query str = sf
//				.getCurrentSession()
//				.createQuery(
//						"select "
//		
//	}
	// select heroes.id as heroesId, resumemarketing.recruiterId, resumemarketing.heroresumeId from heroes left join resumemarketing on heroes.id=resumemarketing.heroesId order by heroesId;

	public User checkEmail(String email) {
		Query query = sf.getCurrentSession().createQuery(
				"from User u where u.email=:email");
		query.setParameter("email", email);
		return (User) query.uniqueResult();
	}

	public Heroes findHeroesById(long id) {
		try {
			return (Heroes) sf.getCurrentSession().load(Heroes.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public User getActiveHeroById(long id) {
		Query str = sf
				.getCurrentSession()
				.createQuery(
						"from User u where u.enabled=:enabled and u.userType=:userType and u.id=:heroId");
		str.setParameter("enabled", Constant.ENABLED);
		str.setParameter("userType", Constant.HERO);
		str.setParameter("heroId", id);
		return (User) str.uniqueResult();
	}

	public long deleteHero(long id) {
		try {
			Query query = sf.getCurrentSession().createQuery(
					"update User u set u.enabled=:enabled where u.id=:id");
			query.setParameter("enabled", Constant.DISABLED);
			query.setParameter("id", id);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void sendResetLink(String link, String email) {
		mailService.sendEmailRequest(email, "Password Reset", link);
	}

	public long updateUserToken(User user) {
		User updatedUser = (User) sf.getCurrentSession().merge(user);
		if (updatedUser != null) {
			return updatedUser.getId();
		}
		return 0;
	}

	public List<User> getRecruiter(long userId) {
		try {
			Query query = sf
					.getCurrentSession()
					.createQuery(
							"from User u where u.id!=:id and u.userType=:userType and u.enabled=:enabled");
			query.setParameter("id", userId);
			query.setParameter("userType", Constant.OTHER);
			query.setParameter("enabled", Constant.ENABLED);
			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
