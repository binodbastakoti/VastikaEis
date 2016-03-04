package com.vastikaEIS.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vastikaEIS.domain.User;

@Component
public class ConfirmPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user.getPassword() == null || user.getConfirmPassword() == null || user.getPassword().length() == 0 || user.getConfirmPassword().length() == 0) {
			errors.rejectValue("password", "not.Empty");
			errors.rejectValue("confirmPassword", "not.Empty");
		} else {
			if (!user.getConfirmPassword().equals(user.getPassword())) {
				errors.rejectValue("confirmPassword", "password.mismatch");
			}
		}

	}
}
