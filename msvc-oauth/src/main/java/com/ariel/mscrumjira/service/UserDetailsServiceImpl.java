package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.UserFeignClient;
import com.ariel.mscrumjira.dto.UserDto;

import feign.FeignException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserFeignClient userClient;	 	 
	 
	public UserDetailsServiceImpl(UserFeignClient userClient) {		
		this.userClient = userClient;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("*********************Username: entarndo a details    *******");
		try {
			UserDto user = userClient.findByUsername(username);
			System.out.println("*********************Username: " + user.getUsername()+"    *******");
			 List<GrantedAuthority> roles = user.getRoles()
					 							.stream()
					 							.map(role-> new SimpleGrantedAuthority(role.name()))
					 							.collect(Collectors.toList());
			 User.UserBuilder builder = User.withUsername(user.getUsername());
			 builder.password("{noop}123");
			 builder.authorities(roles);
			 return builder.build();
				
		} catch (FeignException.NotFound e) {
			System.out.println("*********************fallando details    *******");
			throw new UsernameNotFoundException(e.getMessage());
		}				
				
	}
}