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
		String sql = "insert into Comment(article_id,user_id,comment_text)values (?,?,?)";
		Object[] params = { comment.getArticleId(), comment.getUserId(), comment.getCommentText() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Comment comment) {
		String sql = "update Comment set comment_text=? where user_id=? and article_id=?,";
		Object[] params = { comment.getCommentText(), comment.getUserId(), comment.getArticleId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from Comment where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Comment> list() {
		final String sql = "select id,article,user_id from Comment";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
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
