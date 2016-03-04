package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Heroes;
import com.vastikaEIS.domain.ResumeMarketing;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.request.HeroesDto;
import com.vastikaEIS.dto.request.UsersDto;

/**
 *
 * @author Devil
 */
public interface UserService {

	public boolean doLogin(User user);

	public boolean deleteUser(long id);

	public List<User> getAllUsers();

	public User findUserById(long id);

	public User findUserByUsername(String username);

	public User findUserByEamil(String email);

	public Long addUser(UsersDto usersDto, User applicationUser);

	public User findOtherUserByUsername(String email, long id);

	public Long updateUser(UsersDto usersDto, User applicationUser, User user);

	public long addHero(HeroesDto heroesDto, User applicationUser);

	public Heroes findHeroesById(long id);

	public List<User> getAllHeroes();

	public List<Heroes> getAllAssignedHeroes(long recruiterId);

	public long updateHero(HeroesDto heroesDto, long id, User applicationUser);

	public long deleteHero(long id);

	public User findActiveHeroById(long id);

	public User checkEmail(String email);

	public void sendResetLink(String link, String email);

	public long updateUserToken(User user);

	public List<User> getRecruiter(long userId);

}
