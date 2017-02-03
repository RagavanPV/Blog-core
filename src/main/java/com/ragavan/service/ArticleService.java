package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.dao.ArticleDAO;
import com.ragavan.dao.UserDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;
import com.ragavan.model.User;
import com.ragavan.validator.ArticleValidator;

public class ArticleService {
	ArticleDAO dao = new ArticleDAO();
	ArticleValidator articleValidator = new ArticleValidator();

	public int saveService(Article article) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return dao.save(article);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateService(Article article,String title) throws ServiceException {
		try {
			articleValidator.validateUpdate(article,title);
			return dao.update(article,title);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			articleValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Article> listService() {
		return dao.list();
	}

	public List<Article> listByUserService(int userId) throws ServiceException {
		try {
			articleValidator.validateListByUser(userId);
			return dao.listByUser(userId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public boolean publishArticleService(Article article, User user) throws ServiceException {
		try {
			articleValidator.validateSave(article);
			return dao.publishArticle(article, user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public boolean updateArticleService(Article article, User user,String title) throws ServiceException {
		try {
			articleValidator.validateUpdate(article,title);
			return dao.updateArticle(article, user,title);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public boolean deleteArticleService(Article article, User user) throws ServiceException {
		try {UserDAO userdao=new UserDAO(); 
		int articleId=dao.functionGetArticleId(article.getTitle(), userdao.functionGetUserId(user.getUserName()));
			articleValidator.validateDelete(articleId);
			ArticleCategoryDAO articleCategoryDAO=new ArticleCategoryDAO();
			articleCategoryDAO.delete(articleId);
			return dao.deleteArticle(article, user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
