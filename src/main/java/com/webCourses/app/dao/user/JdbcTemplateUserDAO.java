package com.webCourses.app.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.webCourses.app.model.user.User;

public class JdbcTemplateUserDAO implements UserDAO {
	private static final String SQL_INSERT_USER = "insert into USER (PASSWORD, EMAIL, NAME, SURNAME, SIGNATURE, ADDRESS, CREATED_DATE) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_USER = "select * from USER";
	private static final String SQL_SELECT_USER_BY_ID = SQL_SELECT_USER + " where USER_ID=?";
	private static final String SQL_UPDATE_USER = "update USER set PASSWORD = ?, EMAIL = ?, SURNAME = ?, set SIGNATURE=?, set ADDRESS=? where USER_ID = ?";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate p_jdbcTemplate) {
		this.jdbcTemplate = p_jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	public long addUser(User p_user) {
		jdbcTemplate.update(SQL_INSERT_USER, p_user.getPassword(), p_user.getEmail(), p_user.getName(),
				p_user.getSurname(), p_user.getCreatedDate());
		long user_id = queryForIdentity();
		p_user.setUserId(user_id);
		return user_id;
	}

	@SuppressWarnings("deprecation")
	private long queryForIdentity() {
		return jdbcTemplate.queryForLong("select user_id from USER order by created_date desc limit 1");
	}

	@Override
	public User getUserById(long p_id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new ParameterizedRowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getLong(1));
				user.setPassword(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setName(rs.getString(4));
				user.setSurname(rs.getString(5));
				user.setCreatedDate(rs.getDate(8));
				return user;
			}
		}, p_id);
	}

	@Override
	public void updateUser(User p_user) {
		jdbcTemplate.update(SQL_UPDATE_USER, p_user.getPassword(), p_user.getEmail(), p_user.getName(),
				p_user.getSurname(), p_user.getCreatedDate(), p_user.getUserId());
	}

	@Override
	public User getUserByEmailAndPassword(String p_email, String p_password) {
		return null;
	}

	@Override
	public boolean existingUserByEmail(String p_email) {
		return false;
	}

	@Override
	public User getUserByEmail(String p_email) {
		return null;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public List<User> getLastAddedUsers(int p_resultNumber) {
		return null;
	}

}