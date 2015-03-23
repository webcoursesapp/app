package com.webCourses.app.model.test.result;

import java.io.Serializable;

import com.webCourses.app.model.test.answer.Answer;

public class AnswerResult implements Serializable {

	private static final long serialVersionUID = 6400390264411623915L;
	private Long answerResultId;
	private TestResult testResult;
	private Answer answer;

	public AnswerResult() {

	}

	public AnswerResult(TestResult testResult, Answer answer) {
		this.testResult = testResult;
		this.answer = answer;
	}

	public Long getAnswerResultId() {
		return answerResultId;
	}

	public void setAnswerResultId(Long answerResultId) {
		this.answerResultId = answerResultId;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public boolean equals(Object p_answer) {
		return this.getAnswer().getAnswerId().equals(((Answer) p_answer).getAnswerId());
	}

}
