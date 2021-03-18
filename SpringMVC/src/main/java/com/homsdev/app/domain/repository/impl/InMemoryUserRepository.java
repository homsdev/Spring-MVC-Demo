package com.homsdev.app.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.homsdev.app.domain.User;
import com.homsdev.app.domain.repository.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void registerUser(User newUser) {
		String SQL = "INSERT INTO USERS (ID," + "USERNAME," + "PASSWORD," + "ROLE," + "ENABLE)"
				+ "VALUES(:id,:username,:password,:role,:enable)";

		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("id", newUser.getUserID());
		userInfo.put("username", newUser.getUsername());
		userInfo.put("password", newUser.getPassword());
		userInfo.put("role", newUser.getRole());
		userInfo.put("enable", newUser.isEnable());

		jdbcTemplate.update(SQL, userInfo);
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserID(rs.getString("ID"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setRole(rs.getString("ROLE"));
			user.setEnable(rs.getBoolean("ENABLE"));
			return user;
		}
	}

}
