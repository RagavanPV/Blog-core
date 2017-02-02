package com.ragavan.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Article {
	private int id;
	private User userId;
	private String title;
	private String content;
	private LocalDateTime publishedDate;
	private LocalDateTime modifiedDate;
	private int status;
}
