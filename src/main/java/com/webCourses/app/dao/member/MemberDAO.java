package com.webCourses.app.dao.member;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.member.Member;
import com.webCourses.app.model.user.User;

@Transactional
public interface MemberDAO {
	long add(Member p_member);

	Member get(long p_memberId);

	void update(Member p_member);
	
	void removeMemberForGroup(long p_groupId);
	
	void remove(Member p_member);
	
	void remove(long p_memberId);
	
	Member getMemberForUserAndCourse(User p_user, Course p_course);
}
