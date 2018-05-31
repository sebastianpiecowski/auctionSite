package com.zai.projekt.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
	
	private ModelMapper modelMapper=new ModelMapper();
	@PreAuthorize("hasRole(USER)")
	@Override
	public UserDTO getUserById(int id) {
		UserDTO userDTO=modelMapper.map(userRepository.getOne(id), UserDTO.class);
		return userDTO;
	}
	@Override
	public UserDTO getUserByEmailAndPassword(String email, String password) {

		UserEntity user=userRepository.findByEmail(email);
		if(passwordEncoder.matches(password, user.getPassword())) {
			return modelMapper.map(user, UserDTO.class);
		}
		else {
			return null;
		}
	}
	@Override
	public boolean addUser(SignUp newUser) {
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
			return true;
		}
		else {
			return false;
		}
	}
	public String getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		String email = user.getUsername();
		return email;
	}
	
}
