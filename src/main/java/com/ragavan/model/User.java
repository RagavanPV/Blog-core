package com.ragavan.model;

import lombok.Data;

@Data
public class User {
	private int id;
	private String userName;
	private String password;
	private String emailId;
	private Role roleId;
	private String activationCode;
	private int activation;
}
