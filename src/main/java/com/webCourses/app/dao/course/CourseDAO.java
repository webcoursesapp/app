package com.webCourses.app.dao.course;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.user.User;

@Transactional
public interface CourseDAO{
	long addCourse(Course p_course);

	Course getCourseById(long p_id);

	void updateCourse(Course p_course);

	List<Course> getCoursesForUser(User p_user);
	
	List<Course> getAll();
	
	List<Course> getAllActive();

	List<Course> getLastAddedCourses(int p_number);

	void remove(Course p_course);
}
