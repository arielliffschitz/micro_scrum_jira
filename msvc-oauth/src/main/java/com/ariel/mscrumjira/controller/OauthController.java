package com.ariel.mscrumjira.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.client.UserFeignClient;
import com.ariel.mscrumjira.dto.LoginDto;
import com.ariel.mscrumjira.dto.UserDto;
import com.ariel.mscrumjira.service.JWTService;

@RestController
public class OauthController {
	
	private final UserFeignClient userClient;
	
	private final JWTService    jwtService;
	
	 
	
	public OauthController(UserFeignClient userClient, JWTService jwtService) {
		this.userClient = userClient;
		this.jwtService = jwtService;
	}
	@GetMapping("/hello")
	public ResponseEntity<?> test (){
		return ResponseEntity.ok("hello word");
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@RequestBody LoginDto loginDto) {
	    UserDto userDto = userClient.login(loginDto);
	    String token = jwtService.generateToken(userDto.getUsername(), userDto.getRoles());

	    Map<String,Object> body = new HashMap<>();
	    body.put("token", token);
	    body.put("user", userDto);
	    body.put("message", "Login success");

	    return ResponseEntity.ok(body);
	}

	
}
