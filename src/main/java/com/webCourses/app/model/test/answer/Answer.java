package com.webCourses.app.model.test.answer;

import java.io.Serializable;

import com.webCourses.app.model.test.question.Question;
import com.webCourses.app.model.test.result.AnswerResult;

public class Answer implements Serializable {

	private static final long serialVersionUID = 4486412505459927125L;
	private Long answerId;
	private String content;
	private boolean correct;
	private Question question;

	public Answer() {

	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean equals(Object p_answer) {
		if (p_answer instanceof AnswerResult) {
			return this.getAnswerId().equals(((AnswerResult) p_answer).getAnswer().getAnswerId());
		}
		return this.getAnswerId().equals(((Answer) p_answer).getAnswerId());
	}

}
