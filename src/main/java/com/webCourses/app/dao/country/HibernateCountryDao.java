package com.webCourses.app.dao.country;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.country.Country;

@Repository
public class HibernateCountryDao implements CountryDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Country> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(Country.class);
		List<Country> countries = (List<Country>) criteria.list();
		return countries;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}
}
