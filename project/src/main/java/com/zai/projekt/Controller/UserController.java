package com.zai.projekt.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Repository.UserRepository;
import com.zai.projekt.Request.Login;
import com.zai.projekt.Request.SignUp;

@RestController
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value = "auth", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> getUserById() {
		UserDTO user = userService.getLoggedUser();
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PostMapping(value = "login", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> login(@RequestBody Login login) {

		try {
			UserDTO user = userService.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
			if(user!=null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value = "logout", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> logOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response,auth);
	        return new ResponseEntity<Void>(HttpStatus.OK);
	    }
	    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	    
	}
	@PostMapping(value = "sign_up", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> signUp(@RequestBody SignUp signUp) {
			try {
			UserDTO user=userService.addUser(signUp);
			if(user!=null) {
			return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
			}
			
			}
			catch(Exception e) {
				return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
			}
	}
}