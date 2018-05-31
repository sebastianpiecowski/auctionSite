package com.zai.projekt.IService;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.Request.SignUp;

public interface IUserService {
	UserDTO getUserById(int id);
	UserDTO getUserByEmailAndPassowrd(String email, String password);
	boolean addUser(SignUp user);
}