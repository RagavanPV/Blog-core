package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Role;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class UserDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(User user) {
		String sql = "insert into users(username,password,email_id,activation_code)values (?,?,?,?)";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId() ,user.getActivationCode()};
		return jdbcTemplate.update(sql, params);
	}

	public int update(User user) {
		String sql = "update users set username=?,password=?,email_id=?,role_id=? where id=?";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId(), user.getRoleId().getId(),
				user.getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int updateUsername(User user) {
		String sql = "update users set username=? where email_id=?";
		Object[] params = { user.getUserName(), user.getEmailId() };
		return jdbcTemplate.update(sql, params);
	}
	public int activateUser(User user) {
		String sql = "update users set activation=1 where userName=? and activation_code=?";
		Object[] params = { user.getUserName(), user.getActivationCode() };
		return jdbcTemplate.update(sql, params);
	}

	public int updatePassword(User user) {
		String sql = "update users set password=? where email_id=?";
		Object[] params = { user.getPassword(), user.getEmailId() };
		return jdbcTemplate.update(sql, params);
	}
	public int updateRole(User user) {
		String sql = "update users set role_id=? where id=?";
		Object[] params = { user.getRoleId().getId(), user.getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from users where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<User> list() {
		final String sql = "select users.id,username,password,email_id,role_name from users join role on role.id=users.role_id";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmailId(rs.getString("email_id"));
			Role role = new Role();
			role.setRoleName(rs.getString("role_name"));
			user.setRoleId(role);
			return user;
		});
	}

	public User listOne(String name) {
		final String sql = "select id,username,password,email_id,role_id from users where username=?";
		Object[] params = { name };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> fetchData(rs));
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
	public boolean functionIsUserActive(String name) {
		String sql = "select fn_is_user_active(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}

	public int functionGetUserId(String name) {
		String sql = "select fn_get_user_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);
	}

	public boolean functionLogin(User user) throws ValidationException {
		String sql = "select fn_is_valid_login(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { user.getUserName(), user.getPassword() }, Boolean.class);
	}

	public int functionGetRoleId(String user) throws ValidationException {
		String sql = "select fn_get_role_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { user }, Integer.class);
	}

	public String functionGetUserName(int id) throws ValidationException {
		String sql = "select fn_get_user_name(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}

	public String functionGetUserEmail(int id) throws ValidationException {
		String sql = "select email_id from users where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}
	public String getHashedPassword(String userName) throws ValidationException {
		String sql = "select password from users where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userName }, String.class);
	}

}
