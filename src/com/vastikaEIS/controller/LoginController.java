package com.vastikaEIS.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.UserService;
import com.vastikaEIS.util.ConfirmPasswordValidator;
import com.vastikaEIS.util.MessageProperties;
import com.vastikaEIS.util.PasswordEncoderGenerator;
import com.vastikaEIS.util.RandomStringGenerator;
import com.vastikaEIS.util.SecurityUtil;

/**
 *
 * @author Neil
 */
@Controller
@SessionAttributes("username")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private ConfirmPasswordValidator confirmPasswordValidator;

	MessageProperties messageProperties = new MessageProperties();
	Properties properties = messageProperties.getPropValues();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStarted(ModelMap model, HttpSession httpSession,
			HttpServletResponse response) {
		String username = (String) httpSession.getAttribute("r");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		if (username != null) {
			return "redirect:/u";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error",
				"Invalid username and password!");
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg",
				"You've been logged out successfully");
		return "redirect:/";
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/u", method = RequestMethod.GET)
	public String afterLogin(HttpServletRequest request, Model model,
			HttpSession httpSession) {

		List<String> jsFiles = new ArrayList<>();

		File folder = new File(request.getSession().getServletContext()
				.getRealPath("/resources/js/routes/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				jsFiles.add("routes/" + listOfFiles[i].getName());
			}
		}

		folder = new File(request.getSession().getServletContext()
				.getRealPath("/resources/js/controllers/"));
		listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				jsFiles.add("controllers/" + listOfFiles[i].getName());
			}
		}
		// SecurityUtil securityUtil = new SecurityUtil();
		User user = securityUtil.getSessionUser();

		httpSession.setAttribute("r", "p");
		model.addAttribute("jsFiles", jsFiles);
		return "u";
	}

	// Forget password

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(@ModelAttribute("user") User user,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws ParseException {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("invalidEmail",
					properties.getProperty("invalidEmail"));
			return "redirect:/#forgetpassword";
		}

		User currUser = userService.findUserByEamil(user.getEmail());
		if (currUser == null) {
			redirectAttributes.addFlashAttribute("invalidEmail",
					properties.getProperty("noAssociatedEmail"));
			return "redirect:/#forgetpassword";
		}

		String randomNumber = "";
		try {
			randomNumber = RandomStringGenerator.generateRandomString(16,
					RandomStringGenerator.Mode.ALPHANUMERIC);
		} catch (Exception e) {
			e.printStackTrace();
		}

		currUser.setTokenValue(randomNumber);
		long userId = userService.updateUserToken(currUser);
		if (userId > 0) {
			String link = "password_reset/" + userId + "/" + randomNumber;
			userService.sendResetLink(link, user.getEmail());
			redirectAttributes.addFlashAttribute("validEmail",
					properties.getProperty("emailSent"));
			return "redirect:/#forgetpassword";
		} else {
			redirectAttributes.addFlashAttribute("invalidEmail",
					properties.getProperty("failureMsg"));
			return "redirect:/#forgetpassword";

		}

	}

	@RequestMapping(value = "/password_reset/{userId}/{randomNumber}", method = RequestMethod.GET)
	public String resetLink(@ModelAttribute("user") User user,
			@PathVariable long userId, @PathVariable String randomNumber,
			Model model, RedirectAttributes redirectAttributes) {
		User currUser = userService.findUserById(userId);
		if (currUser == null || currUser.getTokenValue() == null
				|| !currUser.getTokenValue().equals(randomNumber)) {
			return "redirect:/403";
		}
		redirectAttributes.addFlashAttribute("validEmail",
				properties.getProperty("resetLinkSent"));
		return "/resetPassword";
	}

	@RequestMapping(value = "/password_reset/{userId}/{randomNumber}", method = RequestMethod.POST)
	public String resetPassword(@ModelAttribute("user") User user,
			@PathVariable long userId, BindingResult result,
			RedirectAttributes redirectAttributes) {
		confirmPasswordValidator.validate(user, result);

		if (result.hasErrors()) {
			return "resetPassword";
		}
		User currUser = userService.findUserById(userId);
		currUser.setPassword(PasswordEncoderGenerator.beCryptPassword(user
				.getPassword()));
		userService.updateUserToken(currUser);
		redirectAttributes.addFlashAttribute("passwordReset",
				properties.getProperty("passwordReset"));
		return "login";
	}

}
