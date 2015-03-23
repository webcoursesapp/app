package com.webCourses.app.dao.test.result;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.test.result.TestResult;
import com.webCourses.app.model.user.User;

@Repository
public class HibernateTestResultDao implements TestResultDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public TestResult add(TestResult p_TestResult) {
		getCurrentSession().save(p_TestResult);
		return p_TestResult;
	}

	@Override
	public TestResult get(long p_TestResultId) {
		return (TestResult) getCurrentSession().get(TestResult.class, p_TestResultId);
	}

	@Override
	public void update(TestResult p_TestResult) {
		getCurrentSession().update(p_TestResult);
	}

	@Override
	public void remove(TestResult p_TestResult) {
		getCurrentSession().delete(p_TestResult);
	}

	@Override
	public void remove(long p_memberId) {
		getCurrentSession().delete(this.get(p_memberId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTestResultForUser(User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(TestResult.class);
		criteria.add(Restrictions.eq("user", p_user));
		List<TestResult> TestResults = (List<TestResult>) criteria.list();
		return TestResults;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTestResultForUserAndTest(User p_user, Test test) {
		Criteria criteria = getCurrentSession().createCriteria(TestResult.class);
		criteria.add(Restrictions.eq("user", p_user));
		criteria.add(Restrictions.eq("test", test));
		List<TestResult> TestResults = (List<TestResult>) criteria.list();
		return TestResults;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTestResultForCourse(Course p_course) {
		Criteria criteria = getCurrentSession().createCriteria(TestResult.class);
		criteria.add(Restrictions.eq("test.course", p_course));
		List<TestResult> TestResults = (List<TestResult>) criteria.list();
		return TestResults;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public TestResult getTestResultForTest(Test test) {
		Criteria criteria = getCurrentSession().createCriteria(TestResult.class);
		criteria.add(Restrictions.eq("test", test));
		List<TestResult> TestResults = (List<TestResult>) criteria.list();
		return TestResults.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResult> getTestResultForCourseAndUser(Course p_course, User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(TestResult.class);
		criteria.add(Restrictions.eq("user", p_user));
		List<TestResult> result = (List<TestResult>) criteria.list();
		return result;
	}
	
}
