package com.webCourses.app.dao.country;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.webCourses.app.model.country.Country;

@Transactional
public interface CountryDAO {

	List<Country> getAll();
}
