package com.ragavan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ragavan.model.Article;
import com.ragavan.model.ArticleCategory;
import com.ragavan.model.Category;
import com.ragavan.model.User;
import com.ragavan.util.ConnectionUtil;

public class CategoryDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int save(Category category) {
		String sql = "insert into category(name,user_id)values (?,?)";
		Object[] params = { category.getName(), category.getUserId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int update(Category category) {
		String sql = "update category set name=? where id=?";
		Object[] params = { category.getName(), category.getId() };
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

	public List<Category> listCategory() {
		final String sql = "select distinct name from category";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Category category = new Category();
			category.setName(rs.getString("name"));
			return category;
		});
	}

	public List<Category> listByUserId(int userId) {
		final String sql = "select id,name from category where user_id=?";
		Object[] params = { userId };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			final Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			return category;
		});
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

	public int functionGetCategoryId(String name, int userIdVar) {
		String sql = "select fn_get_category_id(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name, userIdVar }, Integer.class);
	}

	public void insertCategory(Article article, Category category) {
		ArticleDAO articleDAO = new ArticleDAO();
		int articleId = articleDAO.functionGetArticleId(article.getTitle(), article.getUserId().getId());
		save(category);
		int categoryId = functionGetCategoryId(category.getName(), article.getUserId().getId());
		ArticleCategory articleCategory = new ArticleCategory();
		article.setId(articleId);
		category.setId(categoryId);
		articleCategory.setArticleId(article);
		articleCategory.setCategoryId(category);
		ArticleCategoryDAO articleCategoryDAO = new ArticleCategoryDAO();
		articleCategoryDAO.save(articleCategory);
	}

	public List<Category> viewCategory(String nam) {
		String sql = "Select id from category where name=?";
		Object[] params = { nam };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			Category category = new Category();
			category.setId(rs.getInt("id"));
			return category;
		});
	}

	public List<Article> viewByCategory(String nam) {
		CategoryDAO categoryDAO = new CategoryDAO();
		final List<Category> category = categoryDAO.viewCategory(nam);
		List<Article> listArticle = null;
		for (final Category a : category) {
			String sql = "select title,content from articles join article_category on article_id=articles.id where category_id=?";
			Object[] params = { a.getId() };
			listArticle = jdbcTemplate.query(sql, params, (rs, rowNum) -> {
				Article article = new Article();
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				return article;
			});
		}
		return listArticle;
	}

}
