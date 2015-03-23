package com.webCourses.app.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.material.MaterialDAO;
import com.webCourses.app.dao.member.MemberDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.material.Material;
import com.webCourses.app.model.member.Member;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;
import com.webCourses.app.services.EmailService;
import com.webCourses.app.utils.ApplicationUtils;

@Controller
@SessionAttributes({ "member", "groups", "course" })
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseDAO CourseDao;
	@Autowired
	private UserDAO UserDao;
	@Autowired
	private MaterialDAO MaterialDao;
	@Autowired
	private GroupDAO GroupDao;
	@Autowired
	private MemberDAO MemberDao;
	@Autowired
	private EmailService mailService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Locale locale, ModelMap model) {
		Course course = new Course();
		course.setUser(this.getLoggedUser());
		course.setActive(true);
		model.addAttribute("course", course);
		return "course/addCourse";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSubmit(@Valid Course course, BindingResult result, Locale locale, ModelMap model) {
		if (result.hasErrors()) {
			return "course/addCourse";
		}
		CourseDao.addCourse(course);
		return "redirect:/course/manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView manage(Locale locale) {
		ModelAndView model = new ModelAndView();
		model.addObject("courseList", CourseDao.getCoursesForUser(this.getLoggedUser()));
		model.addObject("isMember", false);
		model.setViewName("course/coursesList");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Locale locale, @RequestParam(value = "id") int courseId, ModelMap model) {
		Course course = CourseDao.getCourseById(courseId);
		if (!isCourseOwner(course)) {
			return "error";
		}
		model.addAttribute("course", course);
		return "course/editCourse";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editSubmit(@Valid Course course, BindingResult result, Locale locale, ModelMap model) {
		if (result.hasErrors()) {
			return "course/editCourse";
		}
		CourseDao.updateCourse(course);
		return "redirect:/course/manage";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Locale locale) {
		ModelAndView model = new ModelAndView();
		List<Course> courses = CourseDao.getAllActive();
		for (Course course : courses) {
			course.setMember(ApplicationUtils.isCourseMember(GroupDao, course, getLoggedUser()));
		}
		model.addObject("courseList", courses);
		model.setViewName("course/coursesList");
		return model;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Locale locale, @RequestParam(value = "id") int courseId) {
		ModelAndView model = new ModelAndView();
		Course courseToDisplay = CourseDao.getCourseById(courseId);
		model.addObject("course", courseToDisplay);
		List<Test> tests = new ArrayList<Test>(courseToDisplay.getTests());
		List<Test> solvedTests = new ArrayList<Test>();
		tests.removeAll(solvedTests);
		model.addObject("notSolvedTests", tests);
		model.addObject("solvedTests", solvedTests);
		model.addObject("material", new Material());
		model.addObject("isCourseOwner", isCourseOwner(courseToDisplay));
		model.addObject("isMember", ApplicationUtils.isCourseMember(GroupDao, courseToDisplay, getLoggedUser()));
		model.setViewName("course/showCourse");
		return model;
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ModelAndView my(Locale locale) {
		ModelAndView model = new ModelAndView();
		model.addObject("courseList", GroupDao.getCourseForUser(this.getLoggedUser()));
		model.addObject("isMember", true);
		model.setViewName("course/coursesList");
		return model;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, @RequestParam(value = "id") int courseId, ModelMap model) {
		Course course = CourseDao.getCourseById(courseId);
		if (!isCourseOwner(course) && !ApplicationUtils.isCourseMember(GroupDao, course, getLoggedUser())) {
			List<Group> groups = GroupDao.getForCourse(course);
			Member member = new Member();
			member.setUser(getLoggedUser());
			member.setUserId(getLoggedUser().getUserId());
			member.setCourse(course);
			model.addAttribute("member", member);
			model.addAttribute("groups", groups);
			return "course/joinCourse";
		} else {
			model.addAttribute("error", messageSource.getMessage("joinCourse.validation.error.owner", null, locale));
			model.addAttribute("courseList", GroupDao.getCourseForUser(this.getLoggedUser()));
			model.addAttribute("isMember", true);
			return "course/coursesList";
		}
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinSubmit(@Valid Member member, BindingResult result, Locale locale, ModelMap model) {
		Course course = member.getCourse();
		if (result.hasErrors()) {
			return "course/joinCourse";
		}
		if (!course.getPassword().isEmpty()
				&& !course.getPassword().equals(ApplicationUtils.getMd5FromString(member.getPasswordToCourse()))) {
			result.rejectValue("passwordToCourse", "JoinCourse.passwordToCourse.error");
			return "course/joinCourse";
		} else {
			member.setGroup(GroupDao.getById(member.getGroupId()));
			MemberDao.add(member);
		}
		return "redirect:/course/my";
	}

	public boolean isPasswordEmpty(Course p_course) {
		return p_course.getPassword().isEmpty() ? false : true;
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}

	private boolean isCourseOwner(Course p_course) {
		return p_course.getUser().equals(getLoggedUser());
	}

	@RequestMapping(value = "/changeActive", method = RequestMethod.GET)
	public ModelAndView changeActive(Locale locale, @RequestParam(value = "id") int courseId) {
		ModelAndView model = new ModelAndView();
		Course course = CourseDao.getCourseById(courseId);
		if (isCourseOwner(course)) {
			course.setActive(!course.getActive());
			CourseDao.updateCourse(course);
			model.setViewName("redirect:/course/manage");
		} else {
			model.setViewName("error");
		}
		return model;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(Locale locale, @RequestParam(value = "id") Long materialId) {
		ModelAndView model = new ModelAndView();
		Course course = CourseDao.getCourseById(materialId);
		if (isCourseOwner(course)) {
			CourseDao.remove(course);
			model.setViewName("redirect:/course/manage");
		} else {
			model.setViewName("error");
		}
		return model;
	}

}
