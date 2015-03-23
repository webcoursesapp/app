package com.webCourses.app.dao.group;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.user.User;

@Transactional
public interface GroupDAO {
	long add(Group p_course);

	Group getById(long p_groupId);

	void update(Group p_group);
	
	void remove(Group p_group);
	
	void remove(Long p_groupId);

	List<Group> getForUser(User p_user);

	List<Group> getAll();

	List<Group> getForCourse(Course p_course);
	
	List<Group> getGroupForMember(User p_user);

	List<User> getCourseUsers(Course course);

	List<Course> getCourseForUser(User p_user);
}
