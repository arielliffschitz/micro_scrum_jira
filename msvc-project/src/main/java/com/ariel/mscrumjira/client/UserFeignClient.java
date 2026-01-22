package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-user", path="/team")
public interface UserFeignClient {		 	 

	@GetMapping("/exist")
	public boolean existsByTeamKey(@RequestParam String teamKey);
}
