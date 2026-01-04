package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ariel.mscrumjira.dto.LoginDto;
import com.ariel.mscrumjira.dto.UserDto;

@FeignClient(name = "msvc-user", path = "/user")
public interface UserFeignClient {
	
	 @GetMapping("/username/{username}")
	 public UserDto findByUsername(@PathVariable String username);
	 
	 @PostMapping("/login")
	 public  UserDto login(@RequestBody LoginDto loginDto);

}
