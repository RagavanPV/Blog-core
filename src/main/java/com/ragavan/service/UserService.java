package com.ragavan.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.ragavan.dao.UserDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.User;
import com.ragavan.validator.UserValidator;

public class UserService {
	UserDAO dao = new UserDAO();
	UserValidator userValidator = new UserValidator();

	public int saveService(User user) throws ServiceException {
		try {
			userValidator.validateSave(user);
			return dao.save(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (DuplicateKeyException e) {
			throw new ServiceException("Name or email_id already exists", e);
		}
	}

	public int updateService(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			return dao.update(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateUsernameService(User user) throws ServiceException {
		try {
			userValidator.validateUpdateUsername(user);
			return dao.updateUsername(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updatePasswordService(User user) throws ServiceException {
		try {
			userValidator.validateUpdatePassword(user);
			return dao.updatePassword(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int deleteService(int id) throws ServiceException {
		try {
			userValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<User> listService() {
		return dao.list();
	}

	public User listOneService(String name) throws ServiceException {
		try {
			userValidator.validateListOne(name);
			return dao.listOne(name);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean functionLoginService(User user) throws ServiceException {
		try {
			userValidator.validateFunctionLogin(user);
			return dao.functionLogin(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int functionGetRoleId(String name) throws ServiceException {
		try {
			userValidator.validateGetRole(name);
			return dao.functionGetRoleId(name);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int functionGetUserId(String user) throws ServiceException {
		try {
			userValidator.validateGetUserId(user);
			return dao.functionGetUserId(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public String functionGetUserName(int id) throws ServiceException {
		try {
			return dao.functionGetUserName(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public String functionGetUserEmail(int id) throws ServiceException {
		try {
			return dao.functionGetUserEmail(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
