package com.zai.projekt.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Model.UserRole;
import com.zai.projekt.Repository.UserRepository;
import com.zai.projekt.Request.SignUp;

@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO getUserById(int id) {
		return new UserDTO(userRepository.getOne(id));
	}
	@Override
	public UserDTO getUserByEmailAndPassword(String email, String password) {

		UserEntity user=userRepository.findByEmail(email);
		if(passwordEncoder.matches(password, user.getPassword())) {
			return new UserDTO(user);
		}
		else {
			return null;
		}
	}
	@Override
	public UserDTO addUser(SignUp newUser) {
		if(userRepository.findByEmail(newUser.getEmail())==null) {
			UserEntity user=new UserEntity();
			user.setEmail(newUser.getEmail());
			user.setPassword(passwordEncoder.encode(newUser.getPassword()));
			user.setName(newUser.getName());
			user.setSurname(newUser.getSurname());
			user.setPhoneNumber(newUser.getPhoneNumber());
			user.setCity(newUser.getCity());
			user.setRole(UserRole.USER);
			userRepository.save(user);
			return new UserDTO(user);
		}
		else {
			return null;
		}
	}
	@Override
	public UserDTO getLoggedUser() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDTO user=new UserDTO(userRepository.findByEmail(auth.getName()));
		return user;
	}
	@Override
	public UserDTO getUserByEmail(String email) {
		UserDTO user = new UserDTO(userRepository.findByEmail(email));
		return user;
	}
	
}
