package com.ariel.scrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-project", path = "/sprint")
public interface SprintFeignClient {	

	@GetMapping("/exist-team")
	public boolean existsByTeamKey(@RequestParam String teamKey);
}