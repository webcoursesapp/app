package com.webCourses.app.dao.test.question;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.question.Question;

@Transactional
public interface QuestionDAO {
	long add(Question p_question);

	Question get(long p_questionId);

	void update(Question p_qustion);

	void remove(Question p_question);

	void remove(long p_questionId);

	List<Question> getQuestionForTest(Test p_test);
}
