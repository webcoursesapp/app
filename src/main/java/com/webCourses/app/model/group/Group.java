package com.webCourses.app.model.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.member.Member;
import com.webCourses.app.model.user.User;

public class Group implements Serializable {

	private static final long serialVersionUID = 5339629245240847444L;

	private Long groupId;
	@NotEmpty
	private String name;
	private List<Member> members;
	private Course course;
	@NotNull
	private Long courseId;
	private User createdUser;
	private Date createdAt;

	public Group() {
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		List<Member> result = new ArrayList<Member>();
		for (Member member : members) {
			if (member != null) {
				result.add(member);
			}
		}
		this.members = result;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		for (Member member : this.getMembers()) {
			allUsers.add(member.getUser());
		}
		return allUsers;
	}

}
