package com.webCourses.app.model.test.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;

public class TestResult implements Serializable{

	private static final long serialVersionUID = -5453393398407570777L;
	private Long testResultId;
	private Test test;
	private User user;
	private Date startDate;
	private Date endDate;
	private List<QuestionResult> questionResults;
	private List<AnswerResult> answerResults;

	public Long getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Long testResultId) {
		this.testResultId = testResultId;
	}

	public void setTest(Test p_test) {
		this.test = p_test;
	}

	public Test getTest() {
		return test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<QuestionResult> getQuestionResults() {
		return questionResults;
	}

	public void setQuestionResults(List<QuestionResult> questionResults) {
		List<QuestionResult> result = new ArrayList<QuestionResult>();
		for (QuestionResult qR : questionResults) {
			if (qR != null) {
				result.add(qR);
			}
		}
		this.questionResults = result;
	}

	public List<AnswerResult> getAnswerResults() {
		return answerResults;
	}

	public void setAnswerResults(List<AnswerResult> answerResults) {
		List<AnswerResult> result = new ArrayList<AnswerResult>();
		for (AnswerResult aR : answerResults) {
			if (aR != null) {
				result.add(aR);
			}
		}
		this.answerResults = result;
	}

}
