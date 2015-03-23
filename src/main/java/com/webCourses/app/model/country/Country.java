package com.webCourses.app.model.country;

import java.io.Serializable;

public class Country implements Serializable {

	private static final long serialVersionUID = -1550117297647282891L;
	private Long countryId;
	private String name;
	private String code;
	private String continent;
	private String region;

	public Country() {

	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
