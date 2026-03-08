package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;



@FeignClient(name = "msvc-user")
public interface UserFeignClient {

	@GetMapping("/exist")
	public boolean existByUsername(@RequestParam String username);
}
