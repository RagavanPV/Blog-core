package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Article;
import com.ragavan.model.Comment;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class CommentDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Comment comment) {
		String sql = "insert into Comments(article_id,user_id,comment_text)values (?,?,?)";
		Object[] params = { comment.getArticleId().getId(), comment.getUserId().getId(), comment.getCommentText() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Comment comment) {
		String sql = "update Comments set comment_text=? where user_id=? and article_id=?,";
		Object[] params = { comment.getCommentText(), comment.getUserId().getId(), comment.getArticleId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from Comments where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Comment> list() {
		final String sql = "select id,article_id,user_id,comment_text from Comments";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	public List<Comment> listByArticleId(int articleId) {
		final String sql = "select id,article_id,user_id,comment_text from Comments where article_id=?";
		Object[] params = { articleId };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> fetchData(rs));
	}

	private Comment fetchData(ResultSet rs) throws SQLException {
		final Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		comment.setArticleId(article);
		comment.setUserId(user);
		comment.setCommentText(rs.getString("comment_text"));
		return comment;
	}
}
