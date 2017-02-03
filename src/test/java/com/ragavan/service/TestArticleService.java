package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Article;
import com.ragavan.model.User;

public class TestArticleService {
	public static void main(String[] args) {
//		Article article=new Article();
		ArticleService articleService = new ArticleService();
//		 article.setTitle("SQL");
//		 article.setContent("SQL,Structured Query Language) is a special-purpose domain-specific"
//		 		+ " language used in programming and designed for managing data held in a relational"
//		 		+ " database management system (RDBMS), or for stream processing in a relational"
//		 		+ " data stream management system (RDSMS).");
//		 User user=new User();
//		 user.setId(1);
//		 article.setUserId(user);
//		 try {
//		 articleService.publishArticleService(article, user);
//		 } catch (ServiceException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		}
		
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
}
