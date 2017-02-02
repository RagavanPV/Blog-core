package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Article;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class ArticleDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Article article) {
		String sql = "insert into articles(user_id,title,content)values (?,?,?)";
		Object[] params = { article.getUserId(), article.getTitle(), article.getContent() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Article article) {
		String sql = "update articles set title=?,content=? where user_id=?";
		Object[] params = { article.getTitle(), article.getContent(), article.getUserId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from articles where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Article> list() {
		final String sql = "select id,user_id,title,content,published_date,modified_date,status from articles";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Article fetchData(ResultSet rs) throws SQLException {
		final Article article = new Article();
		article.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		article.setUserId(user);
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		article.setPublishedDate(rs.getTimestamp("published_date").toLocalDateTime());
		article.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
		article.setStatus(rs.getInt("status"));
		return article;
	}

	public int functionGetArticleId(String name, int userIdVar) {
		String sql = "select fn_get_article_id(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name, userIdVar }, Integer.class);
	}
}
