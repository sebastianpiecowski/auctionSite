package com.zai.projekt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Repository.UserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity getUserById(int id) {
		return userRepository.getOne(id);
	}
}
