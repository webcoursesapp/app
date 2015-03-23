package com.webCourses.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.controllers.test.question.QuestionController;
import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.test.TestDAO;
import com.webCourses.app.dao.test.result.TestResultDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.answer.Answer;
import com.webCourses.app.model.test.question.Question;
import com.webCourses.app.model.test.result.AnswerResult;
import com.webCourses.app.model.test.result.QuestionResult;
import com.webCourses.app.model.test.result.TestResult;
import com.webCourses.app.model.test.summary.TestSummary;
import com.webCourses.app.model.user.User;
import com.webCourses.app.utils.ApplicationUtils;

@Controller
@SessionAttributes({ "test", "testResult" })
@RequestMapping("/test")
public class TestController {

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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(Locale locale, @RequestParam(value = "courseId", required = false) Long courseId, ModelMap model) {
		Test test = new Test();
		Course course = CourseDao.getCourseById(courseId);
		test.setCourse(course);
		test.setUser(getLoggedUser());
		model.addAttribute("test", test);
		return "test/addTest";
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.POST)
	public String addSubmit(@Valid Test test, BindingResult result, Locale locale, ModelMap model) {
		if (result.hasErrors()) {
			return "test/addTest";
		}
		if (test.getRandom() && test.getQuestionsCount() == null) {
			result.rejectValue("questionsCount", "Test.add.questionsCount.error");
			return "test/addTest";
		}
		Long testId = test.getTestId();
		if (test.getTestId() == null) {
			testId = TestDao.add(test);
		} else {
			TestDao.update(test);
		}
		return "redirect:/test/show?id=" + testId;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, @RequestParam(value = "id", required = false) Long courseId, ModelMap model) {
		List<Test> tests = null;
		if (courseId == null) {
			tests = TestDao.getTestForUser(getLoggedUser());
		} else {
			Course course = CourseDao.getCourseById(courseId);
			if (course.getUser().equals(getLoggedUser())) {
				tests = TestDao.getTestForCourse(course);
				model.addAttribute("course", course);
			}
		}
		model.addAttribute("testList", tests);
		return "test/testsList";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(Locale locale, @RequestParam(value = "id", required = false) Long testId, ModelMap model) {
		Test test = TestDao.get(testId);
		if (test.getUser().equals(getLoggedUser())) {
			model.addAttribute("test", test);
		} else {
			return "error";
		}
		return "test/showTest";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Locale locale, @RequestParam(value = "id") int testId, ModelMap model) {
		Test test = TestDao.get(testId);
		if (!isTestOwner(test)) {
			return "error";
		}
		model.addAttribute("test", test);
		return "test/addTest";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(Locale locale, @RequestParam(value = "id") Long testId) {
		Test testToRemove = TestDao.get(testId);
		TestDao.remove(testToRemove);
		return new ModelAndView("redirect:/test/list");
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}

	private boolean isTestOwner(Test p_test) {
		return p_test.getUser().equals(getLoggedUser());
	}

	@RequestMapping(value = "/run", method = RequestMethod.GET)
	public String run(Locale locale, @RequestParam(value = "id") Long testId, ModelMap model) {
		Test test = TestDao.get(testId);
		if (ApplicationUtils.isCourseMember(GroupDao, test.getCourse(), getLoggedUser())) {
			model.addAttribute("test", test);
			return "test/runTestPasswordForm";
		}
		return "error";
	}

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public String runSubmit(Locale locale, Test test, BindingResult result, ModelMap model,
			@RequestParam(value = "password", required = false) String password) {
		test = TestDao.get(test.getTestId());
		List<String> errors = new ArrayList<String>();
		if (!ApplicationUtils.isCourseMember(GroupDao, test.getCourse(), getLoggedUser())) {
			errors.add("User must be a member of course to");
		}
		if (userDoneThisTest(test, getLoggedUser())) {
			errors.add("You already done this test");
		}
		if (password != null && !password.equals(test.getPassword())) {
			model.addAttribute("test", test);
			errors.add("Wrong password");
		}
		if (errors.size() > 0) {
			model.addAttribute("err", errors);
			return "test/runTestPasswordForm";
		} else {
			model.addAttribute("testResult", prepareTestResult(test));
			return "test/runTest";
		}
	}

	private TestResult prepareTestResult(Test test) {
		TestResult testResult = new TestResult();
		testResult.setTest(test);
		testResult.setStartDate(new Date());
		testResult.setUser(getLoggedUser());
		List<QuestionResult> questions = getQuestionResults(test, testResult);
		clearAnswers(questions);
		testResult.setQuestionResults(getQuestionResults(test, testResult));
		return testResult;
	}

	private void clearAnswers(List<QuestionResult> questionResults) {
		for (QuestionResult questionResult : questionResults) {
			for (Answer answer : questionResult.getQuestion().getAnswers()) {
				answer.setCorrect(false);
			}
		}
	}

	@RequestMapping(value = "/saveResult", method = RequestMethod.POST)
	public String saveResult(Locale locale, TestResult testResult, BindingResult result, ModelMap model) {
		testResult.setEndDate(new Date());
		testResult.setAnswerResults(getCheckedAnswers(testResult));
		// save do bazy testResultu
		TestResultDao.add(testResult);
		Test test = TestDao.get(testResult.getTest().getTestId());
		TestSummary testSummary = new TestSummary(test, testResult);
		model.addAttribute("testSummary", testSummary);
		return "test/showSummary";
	}

	private List<AnswerResult> getCheckedAnswers(TestResult testResult) {
		List<AnswerResult> resultAnswer = new ArrayList<AnswerResult>();
		for (QuestionResult questionResult : testResult.getQuestionResults()) {
			Question question = questionResult.getQuestion();
			if (question.getType().equals(QuestionController.QUESTION_TYPE_RADIO)) {
				Answer rightAnswer = question.getAnswers().get(question.getRightAnswerIndex());
				rightAnswer.setCorrect(true);
				resultAnswer.add(new AnswerResult(testResult, rightAnswer));
			}
			if (question.getType().equals(QuestionController.QUESTION_TYPE_CHECKBOX)) {
				for (Answer answer : question.getAnswers()) {
					if (answer.getCorrect()) {
						resultAnswer.add(new AnswerResult(testResult, answer));
					}
				}
			}
		}
		return resultAnswer;
	}

	private List<QuestionResult> getQuestionResults(Test test, TestResult testResult) {
		List<QuestionResult> resultList = new ArrayList<QuestionResult>();
		if (test.getRandom()) {
			List<Question> randomQuestion = new ArrayList<Question>(test.getQuestions());
			Collections.shuffle(randomQuestion);
			for (int i = 0; i < test.getQuestionsCount(); i++) {
				resultList.add(new QuestionResult(testResult, randomQuestion.get(i)));
			}
		} else {
			for (Question question : test.getQuestions()) {
				resultList.add(new QuestionResult(testResult, question));
			}
		}
		return resultList;
	}

	private boolean userDoneThisTest(Test test, User loggedUser) {
//		if (TestResultDao.getTestResultForUserAndTest(loggedUser, test).size() > 0) {
//			return true;
//		}
		return false;
	}
}
