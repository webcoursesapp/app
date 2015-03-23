package com.webCourses.app.dao.course;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.user.User;
import com.webCourses.app.utils.ApplicationUtils;

@Repository
public class HibernateCourseDao implements CourseDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}
	
	@Override
	public long addCourse(Course p_course) {
		p_course.setCreatedAt(new Date());
		if (!p_course.getPassword().isEmpty()) {
			p_course.setPassword(ApplicationUtils.getMd5FromString(p_course.getPassword()));
		}
		getCurrentSession().save(p_course);
		return p_course.getCourseId();
	}

	@Override
	public Course getCourseById(long p_id) {
		return (Course) getCurrentSession().get(Course.class, p_id);
	}

	@Override
	public void updateCourse(Course p_course) {
		getCurrentSession().update(p_course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesForUser(User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("user", p_user));
		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(Course.class);
		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getLastAddedCourses(int p_resultNumber) {
		Criteria criteria = getCurrentSession().createCriteria(Course.class);
		criteria.addOrder(Order.desc("createdAt"));
		criteria.add(Restrictions.eq("active", true));
		criteria.setMaxResults(p_resultNumber);
		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllActive() {
		Criteria criteria = getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("active", true));
		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@Override
	public void remove(Course p_course) {
		getCurrentSession().delete(p_course);
	}

}
