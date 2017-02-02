package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Article;
import com.ragavan.model.Rating;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class RatingDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Rating rating) {
		String sql = "insert into rating(article_id,user_id,rating)values (?,?,?)";
		Object[] params = { rating.getArticleId(), rating.getUserId(), rating.getRating() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Rating rating) {
		String sql = "update Rating set rating=?,like=? where user_id=? and article_id=?,";
		Object[] params = { rating.getRating(), rating.getLike(), rating.getUserId(), rating.getArticleId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from rating where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public List<Rating> list() {
		final String sql = "select id,article,user_id,rating,like from rating";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Rating fetchData(ResultSet rs) throws SQLException {
		final Rating rating = new Rating();
		rating.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		rating.setArticleId(article);
		rating.setUserId(user);
		rating.setRating(rs.getInt("rating"));
		rating.setLike(rs.getInt("like"));
		return rating;
	}
}
