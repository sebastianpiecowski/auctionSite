package com.zai.projekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.IService.IUserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {
	@Autowired
	IUserService userService;

	@GetMapping(value = "user={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<UserDTO>(userService.getUserById(id), HttpStatus.OK);
		
	}
}
