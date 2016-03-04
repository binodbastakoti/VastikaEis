package com.vastikaEIS.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.User;
import com.vastikaEIS.dto.response.JsonResponse;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.serviceImpl.JWTService;
import com.vastikaEIS.util.BCrypt;
import com.vastikaEIS.util.PasswordEncoderGenerator;

@RestController
@RequestMapping("/api")
public class LoginAppController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
	public JsonResponse authenticate(User user) {
		JsonResponse jsonResponse = new JsonResponse();

		if (user.getUsername().trim().length() > 0
				&& user.getUsername() != null) {
			User applicationUser = userService.findUserByUsername(user
					.getUsername());

			System.out.println(applicationUser.getPassword());

			String password = PasswordEncoderGenerator.beCryptPassword(user
					.getPassword());
			System.out.println(password);

			if (applicationUser != null) {
				if (BCrypt.checkpw(user.getPassword(),
						applicationUser.getPassword())) {
					String token = JWTService.createJsonWebToken(
							applicationUser.getId(), 1L);
					jsonResponse.setStatus("success");
					jsonResponse.setToken(token);
					return jsonResponse;
				} else {
					jsonResponse.setStatus("failed");
					jsonResponse.setMessage("Authentication Failed.");
					return jsonResponse;
				}
			}
			jsonResponse.setStatus("failed");
			jsonResponse.setMessage("Username not found.");
			return jsonResponse;
		}
		jsonResponse.setStatus("failed");
		jsonResponse.setMessage("Username required.");
		return jsonResponse;
	}

}
