package com.zai.projekt.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.Repository.UserRepository;
@Component
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
    UserRepository users;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = users.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException(username + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_"+user.getRole().toString())
        );
       }

}
