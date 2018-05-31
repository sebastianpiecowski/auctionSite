package com.zai.projekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Request.Login;
import com.zai.projekt.Request.SignUp;

@RestController
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	IUserService userService;

	@GetMapping(value = "id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<UserDTO>(userService.getUserById(id), HttpStatus.OK);
		
	}
	@PostMapping(value = "login", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO> login(@RequestBody Login login) {
		UserDTO user = userService.getUserByEmailAndPassowrd(login.getEmail(), login.getPassword());
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	@PostMapping(value = "sign_up", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> signUp(@RequestBody SignUp signUp) {
		userService.addUser(signUp);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}