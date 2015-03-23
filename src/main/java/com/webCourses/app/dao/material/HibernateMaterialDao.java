package com.webCourses.app.dao.material;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.material.Material;
import com.webCourses.app.model.test.Test;
import com.webCourses.app.model.user.User;

@Repository
public class HibernateMaterialDao implements MaterialDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public long add(Material p_material) {
		p_material.setCreatedAt(new Date());
		getCurrentSession().save(p_material);
		return p_material.getMaterialId();
	}

	@Override
	public Material getById(long p_id) {
		return (Material) getCurrentSession().get(Material.class, p_id);
	}

	@Override
	public void update(Material p_material) {
		getCurrentSession().update(p_material);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getAllMaterialsForUser(User p_user) {
		Criteria criteria = getCurrentSession().createCriteria(Material.class);
		criteria.add(Restrictions.eq("user", p_user));
		criteria.addOrder(Order.asc("orderNumber"));
		List<Material> materials = (List<Material>) criteria.list();
		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getAllMaterialsForCourse(Course p_course) {
		Criteria criteria = getCurrentSession().createCriteria(Material.class);
		criteria.add(Restrictions.eq("course", p_course));
		criteria.addOrder(Order.asc("orderNumber"));
		List<Material> materials = (List<Material>) criteria.list();
		return materials;
	}

	@Override
	public void remove(Material p_material) {
		getCurrentSession().delete(p_material);
	}

	@Override
	public void remove(Long p_material) {
		this.remove(this.getById(p_material));
	}

	@Override
	public List<Material> getAll() {
		Criteria criteria = getCurrentSession().createCriteria(Material.class);
		List<Material> material = (List<Material>) criteria.list();
		return material;
	}

}
