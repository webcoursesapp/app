package com.webCourses.app.dao.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.user.User;

@Transactional
public interface UserDAO {
	long addUser(User p_user);

	User getUserById(long p_id);

	User getUserByEmailAndPassword(String p_email, String p_password);

	User getUserByEmail(String p_email);

	void updateUser(User p_user);

	boolean existingUserByEmail(String p_email);
	
	List<User> getAll();

	List<User> getLastAddedUsers(int p_resultNumber);
}
