package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.CategoryDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.model.Article;
import com.ragavan.model.Category;
import com.ragavan.model.User;

public class TestUserService {
	public static void main(String[] args) throws ServiceException {

		// Register();
		// login();
		// publishArticle();
		// viewUserArticles();
		// viewAllArticles();
		// updateArticle();
		 viewArticleByCategory();
		// addCategoryForArticle();
		// updateCategory();
		// deleteArticle();
		User user=new User();
		user.setUserName("Ragavan");
		user.setPassword("ragava");
		UserService service=new UserService();
		System.out.println(service.functionLoginService(user));

	}

	private static void deleteArticle() {
		Article article = new Article();
		ArticleService articleService = new ArticleService();
		article.setTitle("DBMS");
		article.setContent("DBMS,Database is a special-purpose domain-specific"
				+ " language used in programming and designed for managing data held in a relational"
				+ " database management system (RDBMS), or for stream processing in a relational"
				+ " data stream management system (RDSMS).");
		User user = new User();
		user.setId(1);
		user.setUserName("Ragavan");
		article.setUserId(user);
		try {
			System.out.println(articleService.deleteArticleService(article));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void updateCategory() throws ServiceException {
		Category category=new Category();
		String oldName="DBMS";
		category.setName("Database");
		User user=new User();
		user.setId(1);
		category.setUserId(user);
		CategoryService categoryService=new CategoryService();
		System.out.println(categoryService.updateService(category));
	}

	private static void addCategoryForArticle() {
		Article article = new Article();
		 article.setTitle("DBMS");
		 article.setContent("Java is an object-oriented language similar to C++, "
		 + "but simplified to eliminate language features that cause common programming errors."
		 + " Java source code files (files with a .java extension) are compiled into a format "
		 + "called bytecode (files with a .class extension), which can then be executed "
		 + "by a Java interpreter.");
		 User user=new User();
		 user.setId(1);
		 article.setUserId(user);
		 Category category=new Category();
		 category.setName("DBMS");
		 category.setUserId(user);
		 CategoryService categoryService=new CategoryService();
		 try {
		 categoryService.insertCategory(article, category);
		 } catch (ServiceException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

	private static void viewArticleByCategory() {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Article> list = categoryDAO.viewByCategory("Second");
		for (Article a : list) {
			System.out.println(a+" "+"test");
		}
	}

	private static void updateArticle() {
		Article article = new Article();
		ArticleService articleService = new ArticleService();
		article.setTitle("DBMS");
		article.setContent("DBMS,Database is a special-purpose domain-specific"
				+ " language used in programming and designed for managing data held in a relational"
				+ " database management system (RDBMS), or for stream processing in a relational"
				+ " data stream management system (RDSMS).");
		User user = new User();
		user.setId(1);
		user.setUserName("Ragavan");
		article.setUserId(user);
		try {
			System.out.println(articleService.updateArticleService(article, user,"SQL"));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void viewAllArticles() {
		ArticleService articleService = new ArticleService();
		final List<Article> articleList = articleService.listService();
		for (final Article a : articleList) {
			System.out.println(a);
		}
	}

	private static void viewUserArticles() {
		ArticleService articleService = new ArticleService();
		try {
			final List<Article> articleList = articleService.listByUserService(1);
			for (final Article a : articleList) {
				System.out.println(a);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void publishArticle() {
		Article article = new Article();
		ArticleService articleService = new ArticleService();
		article.setTitle("SQL");
		article.setContent("SQL,Structured Query Language) is a special-purpose domain-specific"
				+ " language used in programming and designed for managing data held in a relational"
				+ " database management system (RDBMS), or for stream processing in a relational"
				+ " data stream management system (RDSMS).");
		User user = new User();
		user.setId(1);
		article.setUserId(user);
		try {
			System.out.println(articleService.publishArticleService(article, user));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void login() {
		User user = new User();
		user.setUserName("ragav");
		user.setPassword("Pas");
		user.setEmailId("Ragav87@gmail.com");
		UserService service = new UserService();
		try {
			System.out.println(service.functionLoginService(user));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void register() throws ServiceException {
		User user = new User();
		user.setUserName("ragav");
		user.setPassword("Pas");
		user.setEmailId("Ragav87@gmail.com");
		UserService service = new UserService();
		System.out.println(service.saveService(user));
	}

}
