package com.webCourses.app.dao.test.result;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.result.TestResult;
import com.webCourses.app.model.user.User;

@Transactional
public interface TestResultDAO {
	TestResult add(TestResult p_TestResult);

	TestResult get(long p_TestResultId);

	void update(TestResult p_TestResult);

	void remove(TestResult p_TestResult);

	void remove(long p_TestResultId);

	List<TestResult> getTestResultForUser(User p_user);

	List<TestResult> getTestResultForCourse(Course p_course);
	
	List<TestResult> getTestResultForCourseAndUser(Course p_course, User p_user);

	TestResult getTestResultForTest(Test test);

	List<TestResult> getTestResultForUserAndTest(User p_user, Test test);
}
