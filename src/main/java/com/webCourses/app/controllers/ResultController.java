package com.webCourses.app.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.test.TestDAO;
import com.webCourses.app.dao.test.result.TestResultDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.result.TestResult;
import com.webCourses.app.model.test.summary.TestSummary;
import com.webCourses.app.model.user.User;

@Controller
@SessionAttributes({ "resultMap" })
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private CourseDAO CourseDao;
	@Autowired
	private GroupDAO GroupDao;
	@Autowired
	private UserDAO UserDao;
	@Autowired
	private TestDAO TestDao;
	@Autowired
	private TestResultDAO TestResultDao;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public String membersResults(Locale locale, ModelMap model) {
		Map<Course, List<TestSummary>> results = new LinkedHashMap<Course, List<TestSummary>>();
		for (Course course : CourseDao.getCoursesForUser(this.getLoggedUser())) {
			List<TestSummary> testSummaryList = new ArrayList<TestSummary>();
			for (User user : GroupDao.getCourseUsers(course)) {
				for (TestResult testResult : TestResultDao.getTestResultForUser(user)) {
					if (testResult.getTest().getCourse().getCourseId().equals(course.getCourseId())) {
						testSummaryList.add(new TestSummary(testResult.getTest(), testResult));
					}
				}
			}
			if (testSummaryList.size() > 0) {
				results.put(course, testSummaryList);
			}
		}
		model.addAttribute("resultMap", results);
		return "result/resultList";
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String myResults(Locale locale, @RequestParam(value = "id", required = false) Long testId, ModelMap model) {
		Map<Course, List<TestSummary>> results = new LinkedHashMap<Course, List<TestSummary>>();
		for (Course course : GroupDao.getCourseForUser(this.getLoggedUser())) {
			List<TestSummary> testSummaryList = new ArrayList<TestSummary>();
			for (TestResult testResult : TestResultDao.getTestResultForCourseAndUser(course, getLoggedUser())) {
				if (testResult.getTest().getCourse().getCourseId().equals(course.getCourseId())) {
					testSummaryList.add(new TestSummary(testResult.getTest(), testResult));
				}
			}
			if (testSummaryList.size() > 0) {
				results.put(course, testSummaryList);
			}
		}
		model.addAttribute("resultMap", results);
		return "result/resultList";
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}
}
