package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.RatingDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Rating;
import com.ragavan.validator.RatingValidator;

public class RatingService {
	RatingDAO dao = new RatingDAO();
	RatingValidator ratingValidator = new RatingValidator();

	public int saveService(Rating rating) throws ServiceException {
		try {
			ratingValidator.validateSave(rating);
			return dao.save(rating);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateService(Rating rating) throws ServiceException {
		try {
			ratingValidator.validateUpdate(rating);
			return dao.update(rating);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			ratingValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Rating> listService() {
		return dao.list();
	}
}
