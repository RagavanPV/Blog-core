package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.CategoryDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;
import com.ragavan.model.Category;
import com.ragavan.validator.ArticleValidator;
import com.ragavan.validator.CategoryValidator;

public class CategoryService {
	CategoryDAO dao = new CategoryDAO();
	CategoryValidator categoryValidator = new CategoryValidator();

	public int saveService(Category category) throws ServiceException {
		try {
			categoryValidator.validateSave(category);
			return dao.save(category);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateService(Category category,String oldName) throws ServiceException {
		try {
			categoryValidator.validateUpdate(category,oldName);
			return dao.update(category,oldName);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			categoryValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Category> listService() {
		return dao.list();
	}
	
	public void insertCategory(Article article,Category category) throws ServiceException{
		ArticleValidator articleValidator=new ArticleValidator();
		try {
			categoryValidator.validateSave(category);
			articleValidator.validateSave(article);
			dao.insertCategory(article, category);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		
	}
}
