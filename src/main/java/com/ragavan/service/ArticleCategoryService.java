package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.ArticleCategory;
import com.ragavan.validator.ArticleCategoryValidator;

public class ArticleCategoryService {
	ArticleCategoryDAO dao = new ArticleCategoryDAO();
	ArticleCategoryValidator articleCategoryValidator = new ArticleCategoryValidator();

	public int saveService(ArticleCategory articleCategory) throws ServiceException {
		try {
			articleCategoryValidator.validateSave(articleCategory);
			return dao.save(articleCategory);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateService(ArticleCategory articleCategory) throws ServiceException {
		try {
			articleCategoryValidator.validateUpdate(articleCategory);
			return dao.update(articleCategory);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			articleCategoryValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<ArticleCategory> listService() {
		return dao.list();
	}
}
