package com.webCourses.app.model.member;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.user.User;

public class Member {

	private Long memberId;
	private Group group;
	private User user;
	@NotNull
	private Long userId;
	private Date addedDate;
	@NotNull
	private Course course;
	@NotNull
	private Long groupId;
	private String passwordToCourse;
	
	public Member() {
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	};
	
	@Override
	public boolean equals(Object p_object) {
		if (p_object instanceof User) {
			return this.getUser().equals(p_object);
		}
		return super.equals(p_object);
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getPasswordToCourse() {
		return passwordToCourse;
	}

	public void setPasswordToCourse(String passwordToCourse) {
		this.passwordToCourse = passwordToCourse;
	}

}
