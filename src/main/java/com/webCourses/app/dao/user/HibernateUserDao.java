package com.webCourses.app.dao.user;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.user.Role;
import com.webCourses.app.model.user.User;
import com.webCourses.app.utils.ApplicationUtils;

@Repository
public class HibernateUserDao implements UserDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public long addUser(User p_user) {
		p_user.setRole(Role.USER);
		p_user.setCreatedDate(new Date());
		p_user.setPassword(ApplicationUtils.getMd5FromString(p_user.getPassword()));
		getCurrentSession().save(p_user);
		return p_user.getUserId();
	}

	@Override
	public User getUserById(long p_id) {
		return (User) getCurrentSession().get(User.class, p_id);
	}

	@Override
	public void updateUser(User p_user) {
		getCurrentSession().update(p_user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmailAndPassword(String p_email, String p_password) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", p_email)).add(
				Restrictions.eq("password", ApplicationUtils.getMd5FromString(p_password)));
		List<User> users = (List<User>) criteria.list();
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean existingUserByEmail(String p_email) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", p_email));
		List<User> users = (List<User>) criteria.list();
		if (users.size() == 1) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String p_email) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", p_email));
		List<User> users = (List<User>) criteria.list();
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getLastAddedUsers(int p_resultNumber) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.addOrder(Order.desc("createdAt"));
		criteria.setMaxResults(p_resultNumber);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

}
