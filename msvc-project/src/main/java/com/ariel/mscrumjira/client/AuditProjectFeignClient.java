package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;

@FeignClient(name = "msvc-audit", path = "/project")
public interface AuditProjectFeignClient {
	
	@PostMapping 
	public void createProject (@RequestBody  ProjectCreateAuditDto dto,  @RequestHeader("Authorization") String token );
	
	@PostMapping ("/sprint")
	public void createSprint (@RequestBody  SprintCreateAuditDto dto,  @RequestHeader("Authorization") String token );

}
