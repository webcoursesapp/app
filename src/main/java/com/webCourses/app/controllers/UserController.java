package com.webCourses.app.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.dao.country.CountryDAO;
import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.statistic.StatisticWrapper;
import com.webCourses.app.model.user.User;

@Controller
@SessionAttributes("userAttribute")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO UserDao;
	
	@Autowired
	private CourseDAO CourseDao;
	
	@Autowired
	private CountryDAO CountryDao;

	@Autowired
	private GroupDAO GroupDao;
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showUser(Locale locale, @RequestParam(value = "id", required = false) Long userId, Model model) {

		User userToDisplay;
		boolean editable = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userToDisplay = UserDao.getUserByEmail(auth.getName());
		if (userId == null || userToDisplay.getUserId().equals(userId)) {
			editable = true;
		} else {
			userToDisplay = UserDao.getUserById(userId);
		}
		model.addAttribute("userAttribute", userToDisplay);
		model.addAttribute("editable", editable);
		model.addAttribute("countryList", CountryDao.getAll());
		model.addAttribute("statistic", getStatisticForUser(userToDisplay));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/showUser");
		return modelAndView;
	}

	private StatisticWrapper getStatisticForUser(User userToDisplay) {
		StatisticWrapper result = new StatisticWrapper();
		List<Course> courses = CourseDao.getCoursesForUser(userToDisplay);
		int allMembers = 0;
		for (Course course : courses) {
			allMembers += GroupDao.getCourseUsers(course).size();
		}
		result.setMessages(5);
		result.setMembers(allMembers);
		result.setCourses(courses.size());
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute("userAttribute") User userAttribute, Locale locale, Model model) {
		UserDao.updateUser(userAttribute);
		model.addAttribute("userAttribute", userAttribute);
		model.addAttribute("editable", true);
		model.addAttribute("countryList", CountryDao.getAll());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("user/showUser");
		return modelView;
	}
}
