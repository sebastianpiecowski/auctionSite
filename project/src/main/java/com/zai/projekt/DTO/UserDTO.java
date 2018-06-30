package com.zai.projekt.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zai.projekt.Entity.UserEntity;

import lombok.Data;

@Data
public class UserDTO {
	@JsonInclude(Include.NON_NULL)
	private int id;
	@JsonInclude(Include.NON_NULL)
	private String name;
	@JsonInclude(Include.NON_NULL)
	private String surname;
	@JsonInclude(Include.NON_NULL)
	private String email;
	@JsonInclude(Include.NON_NULL)
	private String phoneNumber;
	@JsonInclude(Include.NON_NULL)
	private String city;
	@JsonInclude(Include.NON_NULL)
	private String role;
	public UserDTO(UserEntity user) {
		id=user.getId();
		name=user.getName();
		surname=user.getSurname();
		email=user.getEmail();
		phoneNumber=user.getPhoneNumber();
		city=user.getCity();
		role=user.getRole().toString();
	
	}
}
