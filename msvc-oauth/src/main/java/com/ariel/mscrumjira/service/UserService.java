package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.UserFeignClient;
import com.ariel.mscrumjira.dto.UserLoginDto;

@Service
public class UserService implements UserDetailsService{	

	private UserFeignClient client;	

	public UserService(UserFeignClient client) {		
		this.client = client;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {			
		try {
			UserLoginDto user = client.findForLoginByUsername(username);

			List<GrantedAuthority> roles = user.getRoles()
											   .stream()
											   .map(role-> new SimpleGrantedAuthority("ROLE_" +role))
											   .collect(Collectors.toList());			
			return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getActive(),
					true, true, true,roles);
		} catch (Exception e) {		
			throw new UsernameNotFoundException("This username doesn't exist in BD: "+ username);
		}		
	}
}
