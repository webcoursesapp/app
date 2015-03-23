package com.webCourses.app.controllers.test.question;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.test.TestDAO;
import com.webCourses.app.dao.test.question.QuestionDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.answer.Answer;
import com.webCourses.app.model.test.question.Question;
import com.webCourses.app.model.user.User;

@Controller
@SessionAttributes({ "question", "questionTypes" })
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private CourseDAO CourseDao;
	@Autowired
	private UserDAO UserDao;
	@Autowired
	private TestDAO TestDao;
	@Autowired
	private QuestionDAO QuestionDao;
	@Autowired
	private MessageSource messageSource;
	public static String QUESTION_TYPE_RADIO = "radio";
	public static String QUESTION_TYPE_CHECKBOX = "checkbox";
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.GET)
	public String add(Locale locale, @RequestParam(value = "id", required = false) Long id, ModelMap model,
			@RequestParam(value = "init", defaultValue = "true") String init) {

		if (init.equals("true")) {
			Test test = TestDao.get(id);
			model.addAttribute("question", initQuestion(test));
		} else {
			model.addAttribute("question", QuestionDao.get(id));
		}
		model.addAttribute("questionTypes", getQuestionTypes(locale));
		return "question/addQuestion";
	}

	private Question initQuestion(Test p_test) {
		Question question = new Question();
		question.setTest(p_test);
		List<Answer> answers = new ArrayList<Answer>();
		question.setAnswers(answers);
		return question;
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.POST)
	public String addSubmit(@Valid Question question, BindingResult result, Locale locale, ModelMap model,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "rightAnswer", required = false) Long answerIndex) {
		if (answerIndex != null && question.getType().equals("radio")) {
			for (Answer answer : question.getAnswers()) {
				answer.setCorrect(false);
			}
			question.getAnswers().get(answerIndex.intValue()).setCorrect(true);
		}
		if (answerIndex != null && question.getType().equals("descriptive")) {
			List<Answer> answers = question.getAnswers();
			if(answers.size()>1){
				List<Answer> oneAnswer = new ArrayList<Answer>();
				oneAnswer.add(answers.get(0));
				question.setAnswers(oneAnswer);
				return "question/addQuestion";
			}
		}
		if (action != null && action.equals("addAnswer")) {
			Answer answer = new Answer();
			answer.setQuestion(question);
			question.getAnswers().add(answer);
			return "question/addQuestion";
		}

		if (result.hasErrors()) {
			return "question/addQuestion";
		}
		
		if (question.getQuestionId() == null) {
			QuestionDao.add(question);
		} else {
			QuestionDao.update(question);
		}
		return "redirect:/test/show?id=" + question.getTest().getTestId();
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

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(Locale locale, @RequestParam(value = "id") Long testId) {
		Question questionToRemove = QuestionDao.get(testId);
		Test test = questionToRemove.getTest();
		QuestionDao.remove(questionToRemove);
		return new ModelAndView("redirect:/test/show?id="+test.getTestId());
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}

	public HashMap<String, String> getQuestionTypes(Locale locale) {
		HashMap<String, String> questionTypes = new HashMap<String, String>();
		questionTypes.put(QUESTION_TYPE_RADIO, messageSource.getMessage("questionType.single", null, locale));
		questionTypes.put(QUESTION_TYPE_CHECKBOX, messageSource.getMessage("questionType.multiple", null, locale));
		return questionTypes;
	}
}
