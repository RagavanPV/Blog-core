package com.ragavan.validator;

import com.ragavan.model.User;
import static com.ragavan.util.ValidationUtil.*;

import com.ragavan.exception.ValidationException;

public class UserValidator {
	public void validateSave(User user) throws ValidationException {
		isInvalidString(user.getUserName(), "Invalid name");
		isInvalidString(user.getPassword(), "Invalid password");
		isInvalidString(user.getEmailId(), "Invalid email");
		isInvalidInteger(user.getRoleId().getId(), "Invalid Role Id");
	}

	public void validateUpdate(User user) throws ValidationException {
		isInvalidString(user.getUserName(), "Invalid name");
		isInvalidString(user.getPassword(), "Invalid password");
		isInvalidString(user.getEmailId(), "Invalid email");
		isInvalidInteger(user.getId(), "Invalid id");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
}
