package com.webCourses.app.dao.test.question;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.question.Question;

@Repository
public class HibernateQuestionDao implements QuestionDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public long add(Question p_test) {
		getCurrentSession().save(p_test);
		return p_test.getQuestionId();
	}

	@Override
	public Question get(long p_questionId) {
		return (Question) getCurrentSession().get(Question.class, p_questionId);
	}

	@Override
	public void update(Question p_member) {
		getCurrentSession().update(p_member);

	}

	@Override
	public void remove(Question p_test) {
		getCurrentSession().delete(p_test);
	}

	@Override
	public void remove(long p_memberId) {
		getCurrentSession().delete(this.get(p_memberId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionForTest(Test p_test) {
		Criteria criteria = getCurrentSession().createCriteria(Test.class);
		criteria.add(Restrictions.eq("test", p_test));
		List<Question> tests = (List<Question>) criteria.list();
		return tests;
	}

}
