package com.webCourses.app.model.test.result;

import java.io.Serializable;

import com.webCourses.app.model.test.question.Question;

public class QuestionResult implements Serializable {

	private static final long serialVersionUID = 5619658050982322975L;
	private Long questionResultId;
	private TestResult testResult;
	private Question question;

	public QuestionResult() {

	}

	public QuestionResult(TestResult testResult, Question question) {
		this.testResult = testResult;
		this.question = question;
	}

	public Long getQuestionResultId() {
		return questionResultId;
	}

	public void setQuestionResultId(Long questionResultId) {
		this.questionResultId = questionResultId;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public boolean equals(Object p_question) {
		return this.getQuestion().getQuestionId().equals(((Question) p_question).getQuestionId());
	}
	

}
