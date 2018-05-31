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
import com.zai.projekt.Request.SignUp;

@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper=new ModelMapper();
	@Override
	public UserDTO getUserById(int id) {
		UserDTO userDTO=modelMapper.map(userRepository.getOne(id), UserDTO.class);
		return userDTO;
	}
	@Override
	public UserDTO getUserByEmailAndPassowrd(String email, String password) {
		return null;
/*
		UserEntity user=userRepository.findByEmail(email);
		if(passwordEncoder.matches(password, user.getPassword())) {
			return modelMapper.map(user, UserDTO.class);
		}
		else {
			return null;
		}*/
	}
	@Override
	public boolean addUser(SignUp newUser) {
		if(userRepository.findByEmail(newUser.getEmail())==null) {
			UserEntity user=new UserEntity();
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setName(newUser.getName());
			user.setSurname(newUser.getSurname());
			user.setPhoneNumber(newUser.getPhoneNumber());
			user.setCity(newUser.getCity());
			userRepository.save(user);
			return true;
		}
		else {
			return false;
		}
	}
	
}
