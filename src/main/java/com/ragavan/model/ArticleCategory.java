package com.ragavan.model;

import lombok.Data;

@Data
public class ArticleCategory {
	private int id;
	private Article articleId;
	private Category categoryId;
}
