package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Role;
import com.ragavan.util.ConnectionUtil;

public class RoleDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Role role) {
		String sql = "insert into Role(role_name)values (?,?)";
		Object[] params = { role.getRoleName() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Role role) {
		String sql = "update Role set role_name=? where id=?";
		Object[] params = { role.getRoleName(), role.getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from Role where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Role> list() {
		final String sql = "select id,role_name from Role";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Role fetchData(ResultSet rs) throws SQLException {
		final Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setRoleName(rs.getString("role_name"));
		return role;
	}
}
