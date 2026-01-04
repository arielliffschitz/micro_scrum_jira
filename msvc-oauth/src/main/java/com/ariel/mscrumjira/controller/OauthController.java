package com.ariel.mscrumjira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.client.UserFeignClient;
import com.ariel.mscrumjira.dto.LoginDto;
import com.ariel.mscrumjira.dto.UserDto;

@RestController
public class OauthController {
	private final UserFeignClient userClient;	 	 
	 
	public OauthController(UserFeignClient userClient) {		
		this.userClient = userClient;
	}
	@GetMapping("/hello")
	public ResponseEntity<?> test (){
		return ResponseEntity.ok("hello word");
	}
	@PostMapping("/login")
	public UserDto login(@RequestBody LoginDto loginDto) {
	    return userClient.login(loginDto);
	}

	
}
