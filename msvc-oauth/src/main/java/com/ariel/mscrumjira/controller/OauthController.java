package com.ariel.mscrumjira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {
	
	
	@GetMapping("/hello2")
	public ResponseEntity<?> test2 (){
		return ResponseEntity.ok("hello word from oauth");
	}
	@GetMapping("/hello")
	public ResponseEntity<?> test (){
		return ResponseEntity.ok("hello word from oauth");
	}
	
}
