package com.ragavan.validator;

import static com.ragavan.util.ValidationUtil.isInvalidInteger;
import static com.ragavan.util.ValidationUtil.isInvalidString;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.Article;

public class ArticleValidator {
	public void validateSave(Article article) throws ValidationException {
		isInvalidInteger(article.getUserId().getId(), "Invalid user id");
		isInvalidString(article.getTitle(), "Invalid title");
		isInvalidString(article.getContent(), "Invalid content");
	}

	public void validateUpdate(Article article,String title) throws ValidationException {
		isInvalidInteger(article.getUserId().getId(), "Invalid user id");
		isInvalidString(article.getTitle(), "Invalid title");
		isInvalidString(article.getContent(), "Invalid content");
		isInvalidString(title, "Invalid Old title");
	}

	public void validateDelete(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid id");
	}
	public void validateListByUser(int id) throws ValidationException {
		isInvalidInteger(id, "Invalid User id");
	}
}
