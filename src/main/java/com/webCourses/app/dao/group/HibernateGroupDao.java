package com.webCourses.app.dao.group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.user.User;

@Repository
public class HibernateGroupDao implements GroupDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}
	
	@Override
	public long add(Group p_course) {
		p_course.setCreatedAt(new Date());
		getCurrentSession().save(p_course);
		return p_course.getCourseId();
	}

	@Override
	public Group getById(long p_groupId) {
		return (Group) getCurrentSession().get(Group.class, p_groupId);
	}

	@Override
	public void update(Group p_group) {
		getCurrentSession().update(p_group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getForUser(User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(Group.class);
		criteria.add(Restrictions.eq("createdUser", p_user));
		List<Group> groups = (List<Group>) criteria.list();
		return groups;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(Group.class);
		List<Group> groups = (List<Group>) criteria.list();
		return groups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getForCourse(Course p_course) {
		Criteria criteria = getCurrentSession().createCriteria(Group.class);
		criteria.add(Restrictions.eq("course", p_course));
		List<Group> groups = (List<Group>) criteria.list();
		return groups;
	}

	@Override
	public List<Group> getGroupForMember(User p_user) {
		List<Group> groups = new ArrayList<Group>();
		for (Group group : getAll()) {
			if (group.getMembers().contains(p_user)) {
				groups.add(group);
			}
		}
		return groups;
	}

	@Override
	public void remove(Group p_group) {
		getCurrentSession().delete(p_group);
	}

	@Override
	public void remove(Long p_groupId) {
		this.remove(this.getById(p_groupId));
	}

	@Override
	public List<User> getCourseUsers(Course p_course) {
		List<Group> groups = this.getForCourse(p_course);
		List<User> resultList = new ArrayList<User>();
		for (Group group : groups) {
			resultList.addAll(group.getAllUsers());
		}
		return resultList;
	}
	@Override
	public List<Course> getCourseForUser(User p_user) {
		List<Group> groups = this.getGroupForMember(p_user);
		List<Course> courses = new ArrayList<Course>();
		for (Group group : groups) {
			courses.add(group.getCourse());
		}
		return courses;
	}
	
}
