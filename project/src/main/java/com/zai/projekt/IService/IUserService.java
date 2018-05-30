package com.zai.projekt.IService;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.UserEntity;

public interface IUserService {
	UserDTO getUserById(int id);
}
