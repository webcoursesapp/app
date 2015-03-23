package com.webCourses.app.model.user;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.webCourses.app.model.member.Member;

public class User implements Serializable {
	private static final long serialVersionUID = 3707781596036597417L;
	private Long userId;
	@NotEmpty @Email
	private String email;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;
	private String phone;
	@NotEmpty
	private String password;
	private String newPassword;
	private String lostPasswordCode;
	private Date lostPasswordDate;
	private String confirmationCode;
	private boolean enabled;
	private Role role;
	private String avatar;
	private Date createdAt;
	private String description;
	@NotEmpty
	private String country;
	private Date birthdayDate;
	private String url;

	public User() {
	}

	public User(String email, String name, String surname, String password) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdAt;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdAt = createdDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getLostPasswordCode() {
		return lostPasswordCode;
	}

	public void setLostPasswordCode(String lostPasswordCode) {
		this.lostPasswordCode = lostPasswordCode;
	}

	public Date getLostPasswordDate() {
		return lostPasswordDate;
	}

	public void setLostPasswordDate(Date lostPasswordDate) {
		this.lostPasswordDate = lostPasswordDate;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return this.name + " " + this.surname;
	}
	
	public String getLabel(){
		return this.name + " " + this.surname;
	}

	public boolean validateDataToRegister() {
		if (email == null || email.equals("") || password == null || password.equals("") || name == null
				|| name.equals("") || surname == null || surname.equals("")) {
			return false;
		}
		return true;
	}

	public String getValidationMessage() {
		String validationMessage = "Proszê wype³niæ:";
		if (email == null || email.equals("")) {
			validationMessage += " email";
		}
		if (password == null || password.equals("")) {
			validationMessage += " has³o";
		}
		if (name == null || name.equals("")) {
			validationMessage += " imiê";
		}
		if (surname == null || surname.equals("")) {
			validationMessage += " nazwisko";
		}
		return validationMessage;
	}
	
	@Override
	public boolean equals(Object p_object) {
		if (p_object instanceof Member) {
			return this.getUserId() != null && this.getUserId().equals(((Member) p_object).getUser().getUserId());
		}
		if (p_object instanceof User) {
			return this.getUserId() != null && this.getUserId().equals(((User) p_object).getUserId());
		}
		return super.equals(p_object);
	}
}
