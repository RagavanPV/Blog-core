package com.ragavan.service;

import java.util.List;

import com.ragavan.dao.RoleDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Role;
import com.ragavan.validator.RoleValidator;

public class RoleService {
	RoleDAO dao = new RoleDAO();
	RoleValidator roleValidator = new RoleValidator();

	public int saveService(Role role) throws ServiceException {
		try {
			roleValidator.validateSave(role);
			return dao.save(role);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateService(Role role) throws ServiceException {
		try {
			roleValidator.validateUpdate(role);
			return dao.update(role);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			roleValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<Role> listService() {
		return dao.list();
	}
}
