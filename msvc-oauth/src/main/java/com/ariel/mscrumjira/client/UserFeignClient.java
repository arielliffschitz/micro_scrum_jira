package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ariel.mscrumjira.dto.UserLoginDto;

@FeignClient(name = "msvc-user")
public interface UserFeignClient {
	
	@GetMapping("/user-login/{username}")
    public UserLoginDto findForLoginByUsername(@PathVariable String username);	 	 

}
