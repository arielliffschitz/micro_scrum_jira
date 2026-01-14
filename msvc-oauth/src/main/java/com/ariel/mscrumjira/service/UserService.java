package com.ariel.mscrumjira.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.ariel.mscrumjira.dto.UserLoginDto;

@Service
public class UserService implements UserDetailsService{	
	
	private WebClient.Builder client;	
	
	public UserService(Builder client) {		
		this.client = client;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		try {
			UserLoginDto user = client.build().get().uri("/user-login/{username}", params)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(UserLoginDto.class)
					.block();
			List<GrantedAuthority> roles = user
					   						.getRoles()
					   						.stream()
					   						.map(role-> new SimpleGrantedAuthority(role))
					   						.collect(Collectors.toList());			
			return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getActive(),
																			true, true, true,roles);
		} catch (Exception e) {		
			throw new UsernameNotFoundException("error en el login no existe el user: "+ username);
		}		
	}

}
