package com.webCourses.app.model.test.summary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webCourses.app.dao.test.TestDAO;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.question.Question;
import com.webCourses.app.model.test.result.TestResult;

public class TestSummary {

	private TestResult testResult;
	private Test test;
	private int rate;
	private List<Question> rightQuestions = new ArrayList<Question>();
	private List<Question> wrongQuestions = new ArrayList<Question>();
	@Autowired
	private TestDAO TestDao;

	public TestSummary(Test test, TestResult testResult) {
		this.testResult = testResult;
		this.test = test;
		prepareReport();
	}

	private void prepareReport() {
		countQuestions();
		generateRate();
	}

	private void countQuestions() {
		for (Question question : test.getQuestions()) {
			if (testResult.getQuestionResults().contains(question)
					&& testResult.getAnswerResults().containsAll(question.getCorrectAnswer())) {
				rightQuestions.add(question);
			} else if (testResult.getQuestionResults().contains(question)
					&& !testResult.getAnswerResults().containsAll(question.getCorrectAnswer())) {
				wrongQuestions.add(question);
			}
		}
	}

	private void generateRate() {
		int rightQuestion = this.rightQuestions.size();
		int allQuestion = this.testResult.getQuestionResults().size();
		double percent = (double) rightQuestion / (double) allQuestion;
		if (percent < 0.3) {
			this.rate = 1;
		} else if (percent >= 0.3 && percent < 0.5) {
			this.rate = 2;
		} else if (percent >= 0.5 && percent < 0.75) {
			this.rate = 3;
		} else if (percent >= 0.75 && percent < 0.90) {
			this.rate = 4;
		} else if (percent >= 0.90) {
			this.rate = 5;
		}
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public List<Question> getRightQuestions() {
		return rightQuestions;
	}

	public void setRightQuestions(List<Question> rightQuestions) {
		this.rightQuestions = rightQuestions;
	}

	public List<Question> getWrongQuestions() {
		return wrongQuestions;
	}

	public void setWrongQuestions(List<Question> wrongQuestions) {
		this.wrongQuestions = wrongQuestions;
	}

}
