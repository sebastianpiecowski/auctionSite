package com.zai.projekt.Request;

import lombok.Data;

@Data
public class SignUp {
	private String email;
	private String password;
	private String name;
	private String surname;
	private String city;
	private String phoneNumber;
}
