package com.webCourses.app.dao.test;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;

@Transactional
public interface TestDAO {
	long add(Test p_test);

	Test get(long p_testId);

	void update(Test p_test);

	void remove(Test p_test);

	void remove(long p_testId);

	List<Test> getTestForUser(User p_user);

	List<Test> getTestForCourse(Course p_course);
	
	List<Test> getAll();
}
