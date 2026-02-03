package com.ariel.mscrumjira.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.ProjectAuditDto;
import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;
import com.ariel.mscrumjira.service.ProjectAuditService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/project")
public class ProjectAuditController {
	
	private final  ProjectAuditService  projectService;
	private final Logger logger = LoggerFactory.getLogger(ProjectAuditController.class);
	
	public ProjectAuditController(ProjectAuditService projectService) {	
		this.projectService = projectService;
	}
	
	@GetMapping
	public List<ProjectAuditDto> findAll() {
		return projectService.findAll();
	}
	
	@GetMapping("/project-key/{projectKey}")
	public ProjectAuditDto findByProjectKey(@PathVariable Integer projectKey) {
		return   projectService.findByProjectKey(projectKey);							
	}
	
	@PostMapping
	public void createProject (@RequestBody @Valid  ProjectCreateAuditDto dto, @RequestHeader("Authorization") String token ){
		logger.info("creating ProjectAudit with projectKey {} ",dto.projectKey());
		projectService.createProject(dto, token);
	}
	@PostMapping("/sprint")
	public void createSprint (@RequestBody @Valid  SprintCreateAuditDto dto, @RequestHeader("Authorization") String token ){
		logger.info("creating SprintAudit with sprintKey {} ",dto.sprintKey());
		projectService.createSprint(dto, token);			
	}
	
}
