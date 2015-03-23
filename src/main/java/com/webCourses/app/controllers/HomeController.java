package com.webCourses.app.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.material.MaterialDAO;
import com.webCourses.app.dao.test.TestDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.statistic.StatisticWrapper;
import com.webCourses.app.model.user.User;

@Controller
public class HomeController {

	@Autowired
	private UserDAO UserDao;

	@Autowired
	private CourseDAO CourseDao;

	@Autowired
	private MaterialDAO MaterialDao;

	@Autowired
	private TestDAO TestDao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {

		ModelAndView model = new ModelAndView();
		// User logged = getLoggedUser();
		// if (logged != null) {
		// model.addObject("courseProgress", CourseDao.getLastAddedCourses(5));
		// model.addObject("lastMessages", CourseDao.getLastAddedCourses(5));
		// model.addObject("lastAddedEvents", CourseDao.getLastAddedCourses(5));
		// model.addObject("endingEvents", CourseDao.getLastAddedCourses(5));
		// } else {
		// model.addObject("lastAddedCourses",
		// CourseDao.getLastAddedCourses(5));
		// model.addObject("lastAddedUsers", UserDao.getLastAddedUsers(5));
		// model.addObject("statistic", getStatisticWrapper());
		// }
		model.setViewName("home/home");
		return model;
	}

	private StatisticWrapper getStatisticWrapper() {
		StatisticWrapper sw = new StatisticWrapper();
		sw.setCourses(CourseDao.getAll().size());
		sw.setUsers(UserDao.getAll().size());
		sw.setHomeworks(14);
		sw.setTests(TestDao.getAll().size());
		sw.setMaterials(MaterialDao.getAll().size());
		sw.setMessages(23);
		return sw;
	}

	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public ModelAndView about(Locale locale, Model model) {
		return new ModelAndView("home/about");
	}

	@RequestMapping(value = { "/help" }, method = RequestMethod.GET)
	public ModelAndView help(Locale locale, Model model) {
		return new ModelAndView("home/help");
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home/admin");
		return model;
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}
}
