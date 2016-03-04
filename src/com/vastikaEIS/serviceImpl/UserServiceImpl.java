package com.vastikaEIS.serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.dao.UserDao;
import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.HeroesDto;
import com.vastikaEIS.dto.request.UsersDto;
import com.vastikaEIS.service.UserService;

/**
 *
 * @author Devil
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findUserById(long id) {
		return userDao.findUserById(id);
	}

	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	public User findUserByEamil(String email) {
		return userDao.findUserByEamil(email);
	}

	@Override
	public User findOtherUserByUsername(String email, long id) {
		return userDao.findOtherUserByUsername(email, id);
	}

	@Override
	public Long addUser(UsersDto usersDto, User applicationUser) {
		return userDao.addUser(usersDto, applicationUser);
	}

	@Override
	public Long updateUser(UsersDto usersDto, User applicationUser, User user) {
		return userDao.updateUser(usersDto, applicationUser, user);
	}

	@Override
	public long addHero(HeroesDto heroesDto, User applicationUser) {
		return userDao.addHero(heroesDto, applicationUser);
	}

	@Override
	public long updateHero(HeroesDto heroesDto, long userId,
			User applicationUser) {
		return userDao.updateHero(heroesDto, userId, applicationUser);
	}

	@Override
	public boolean doLogin(User user) {
		return userDao.doLogin(user);
	}

	@Override
	public boolean deleteUser(long id) {
		return userDao.deleteUser(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Heroes findHeroesById(long id) {
		return userDao.findHeroesById(id);
	}

	@Override
	public List<User> getAllHeroes() {
		return userDao.getAllHeroes();
	}

	@Override
	public List<Heroes> getAllAssignedHeroes(long recruiterId) {
		return userDao.getAllAssignedHeroes(recruiterId);
	}

	@Override
	public User checkEmail(String email) {
		return userDao.checkEmail(email);
	}

	@Override
	public void sendResetLink(String link, String email) {
		userDao.sendResetLink(link, email);
	}

	@Override
	public long deleteHero(long id) {
		return userDao.deleteHero(id);
	}

	public User findActiveHeroById(long id) {
		return userDao.getActiveHeroById(id);
	}

	@Override
	public long updateUserToken(User user) {
		return userDao.updateUserToken(user);
	}

	public List<User> getRecruiter(long userId) {
		return userDao.getRecruiter(userId);
	}
}
