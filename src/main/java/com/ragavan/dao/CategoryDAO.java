package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Category;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class CategoryDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Category category) {
		String sql = "insert into category(name,user_id)values (?,?)";
		Object[] params = { category.getName(), category.getUserId() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Category category) {
		String sql = "update category set name=? where user_id=?";
		Object[] params = { category.getName(), category.getUserId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from category where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Category> list() {
		final String sql = "select id,name,user_id from category";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Category fetchData(ResultSet rs) throws SQLException {
		final Category category = new Category();
		category.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		category.setName(rs.getString("name"));
		category.setUserId(user);
		return category;
	}
}
