package com.webCourses.app.dao.material;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.material.Material;
import com.webCourses.app.model.user.User;

@Transactional
public interface MaterialDAO {
	long add(Material p_material);

	Material getById(long p_id);

	void update(Material p_material);

	List<Material> getAll();
	
	List<Material> getAllMaterialsForUser(User p_user);

	List<Material> getAllMaterialsForCourse(Course p_course);

	void remove(Long materialId);

	void remove(Material p_material);
}
