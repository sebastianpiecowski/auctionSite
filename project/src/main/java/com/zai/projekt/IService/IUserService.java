package com.zai.projekt.IService;

import java.util.List;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.Request.SignUp;

public interface IUserService {
	UserDTO getUserByEmail(String email);
	UserDTO getUserById(int id);
	UserDTO getUserByEmailAndPassword(String email, String password);
	UserDTO getLoggedUser();
	UserDTO addUser(SignUp user);
}