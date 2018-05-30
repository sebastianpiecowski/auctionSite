package com.zai.projekt.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService{
	@Autowired
	private UserRepository userRepository;
	private ModelMapper modelMapper=new ModelMapper();
	@Override
	public UserDTO getUserById(int id) {
		UserDTO userDTO=modelMapper.map(userRepository.getOne(id), UserDTO.class);
		return userDTO;
	}
}
