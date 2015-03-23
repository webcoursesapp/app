package com.webCourses.app.model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.question.Question;
import com.webCourses.app.model.user.User;

public class Test {

	private Long testId;
	@NotNull
	private User user;
	private Long courseId;
	@NotNull
	private Course course;
	@NotEmpty
	private String title;
	private Date created;
	private Date modified;
	private String password;
	@NotNull
	private Integer time;
	private boolean visibility;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	private boolean random;
	private Integer questionsCount;
	private List<Question> questions;

	public Test() {
		
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean getRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}

	public Integer getQuestionsCount() {
		return questionsCount;
	}

	public void setQuestionsCount(Integer questionsCount) {
		this.questionsCount = questionsCount;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		List<Question> result = new ArrayList<Question>();
		for(Question quest : questions){
			if (quest!= null) {
				result.add(quest);
			}
		}
		this.questions = result;
	}

	@Override
	public String toString() {
		return this.title;
	}
}
