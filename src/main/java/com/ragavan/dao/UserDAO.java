package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Role;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class UserDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(User user) {
		String sql = "insert into users(username,password,email_id,role_id)values (?,?,?,?)";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId(), user.getRoleId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(User user) {
		String sql = "update users set username=?,password=?,email_id=? where id=?";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from users where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<User> list() {
		final String sql = "select id,username,password,email_id,role_id from users";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private User fetchData(ResultSet rs) throws SQLException {
		final User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserName(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmailId(rs.getString("email_id"));
		Role role = new Role();
		role.setId(rs.getInt("role_id"));
		user.setRoleId(role);
		return user;
	}

	public boolean functionCheckUserName(String name) {
		String sql = "select fn_is_valid_username(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}

	public boolean functionCheckPassword(String name) {
		String sql = "select fn_is_valid_password(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}

	public int functionGetUserName(String name) {
		String sql = "select fn_get_user_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);
	}
}
