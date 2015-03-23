package com.webCourses.app.model.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.webCourses.app.model.material.Material;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;

public class Course implements Serializable {

	private static final long serialVersionUID = 6130765635376252905L;

	private Long courseId;
	@NotNull
	private User user;
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	private String password;
	private Date createdAt;
	private List<Material> materials;
	private List<Test> tests;
	private boolean isMember = false;
	@NotNull
	private boolean active;
	
	public Course() {

	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		List<Material> result = new ArrayList<Material>();
		for(Material material : materials){
			if (material != null) {
				result.add(material);
			}
		}
		this.materials = result;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		List<Test> result = new ArrayList<Test>();
		for(Test test : tests){
			if (test!= null) {
				result.add(test);
			}
		}
		this.tests= result;
	}

	public boolean getIsMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
