package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.ArticleDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;
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

	public int updateService(Article article) throws ServiceException {
		try {
			articleValidator.validateUpdate(article);
			return dao.update(article);
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
}
