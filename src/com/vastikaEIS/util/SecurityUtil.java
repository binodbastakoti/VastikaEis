package com.vastikaEIS.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.UserService;

@Service
public class SecurityUtil {

	@Autowired
	private UserService userService;

	public User getSessionUser() {
		Authentication authentication = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			User user = userService.findUserByUsername(currentUserName);
			return user;
		}
		return null;
	}

}
