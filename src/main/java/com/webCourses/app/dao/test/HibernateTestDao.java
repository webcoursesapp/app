package com.webCourses.app.dao.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;

@Repository
public class HibernateTestDao implements TestDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public long add(Test p_test) {
		getCurrentSession().save(p_test);
		return p_test.getTestId();
	}

	@Override
	public Test get(long p_testId) {
		return (Test) getCurrentSession().get(Test.class, p_testId);
	}

	@Override
	public void update(Test p_test) {
		getCurrentSession().update(p_test);
	}

	@Override
	public void remove(Test p_test) {
		getCurrentSession().delete(p_test);
	}

	@Override
	public void remove(long p_memberId) {
		getCurrentSession().delete(this.get(p_memberId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getTestForUser(User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(Test.class);
		criteria.add(Restrictions.eq("user", p_user));
		List<Test> tests = (List<Test>) criteria.list();
		return tests;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getTestForCourse(Course p_course) {
		Criteria criteria = getCurrentSession().createCriteria(Test.class);
		criteria.add(Restrictions.eq("course", p_course));
		List<Test> tests = (List<Test>) criteria.list();
		return tests;
	}

	@Override
	public List<Test> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(Test.class);
		List<Test> tests = (List<Test>) criteria.list();
		return tests;
	}

}
