package com.webCourses.app.controllers;

import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.dao.country.CountryDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.user.User;
import com.webCourses.app.services.EmailService;
import com.webCourses.app.utils.ApplicationUtils;

@Controller
public class LoginController {

	@Autowired
	private UserDAO UserDao;
	@Autowired
	private CountryDAO CountryDao;
	@Autowired
	private EmailService mailService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", messageSource.getMessage("login.form.error.invalidUsername", null, locale));
			model.setViewName("login");
		}

		if (logout != null) {
			model.addObject("msg", messageSource.getMessage("login.form.logout.success", null, locale));
			model.setViewName("login");
		}

		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showregisterForm(Locale locale, ModelMap model) {
		model.addAttribute("countryList", CountryDao.getAll());
		model.addAttribute("user", new User());
		return "home/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(@Valid User user, BindingResult result, Locale locale, ModelMap model) {
		model.addAttribute("countryList", CountryDao.getAll());
		boolean emailExistAlready = checkExistingUserEmail(user);
		if (result.hasErrors() || emailExistAlready) {
			if (emailExistAlready) {
				result.rejectValue("email", "login.form.error.emailExistAlready");
			}
			return "home/register";
		}
		user.setConfirmationCode(ApplicationUtils.getMd5FromString(new Date().toString()));
		UserDao.addUser(user);
		model.addAttribute("msg", messageSource.getMessage("login.form.register.success", null, locale));
		mailService.sendRegistrationConfirmation(user, locale);
		return "login";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView confirmAccount(Locale locale, @RequestParam(value = "code") String code,
			@RequestParam(value = "id") Long userId) {
		ModelAndView model = new ModelAndView();
		User user = UserDao.getUserById(userId);
		if (user.getConfirmationCode().equals(code)) {
			user.setEnabled(true);
			UserDao.updateUser(user);
			model.addObject("msg", messageSource.getMessage("login.form.acount.activated", null, locale));
			model.setViewName("login");
		} else {
			model.addObject("error", messageSource.getMessage("login.form.acount.activated.error", null, locale));
			model.setViewName("login");
		}
		return model;

	}

	private boolean checkExistingUserEmail(User user) {
		return UserDao.existingUserByEmail(user.getEmail());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		return "logout";
	}
}
