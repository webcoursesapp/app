package com.webCourses.app.model.test.question;

import java.util.ArrayList;
import java.util.List;

import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.answer.Answer;
import com.webCourses.app.model.test.result.QuestionResult;

public class Question {

	private Long questionId;
	private Test test;
	private String content;
	private String type;
	private List<Answer> answers;
	private int rightAnswerIndex = -1;

	public Question() {
		
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		List<Answer> result = new ArrayList<Answer>();
		for (Answer answer : answers) {
			if (answer != null) {
				result.add(answer);
			}
		}
		this.answers = result;
	}

	public int getRightAnswerIndex() {
		return rightAnswerIndex;
	}

	public void setRightAnswerIndex(int rightAnswerIndex) {
		this.rightAnswerIndex = rightAnswerIndex;
	}
	
	public List<Answer> getCorrectAnswer() {
		List<Answer> result = new ArrayList<Answer>();
		for (Answer answer : answers) {
			if (answer.getCorrect()) {
				result.add(answer);
			}
		}
		return result;
	}
	
	public boolean equals(Object p_question) {
		if(p_question instanceof QuestionResult){
			return this.getQuestionId().equals(((QuestionResult) p_question).getQuestion().getQuestionId());	
		}
		return this.getQuestionId().equals(((Question) p_question).getQuestionId());
	}

}
