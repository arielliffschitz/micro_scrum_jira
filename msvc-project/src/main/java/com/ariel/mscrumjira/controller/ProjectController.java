package com.ariel.mscrumjira.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.service.*;

import jakarta.validation.*;

@RestController
public class ProjectController {
	
	private ProjectService service;
	
	private final Logger logger = LoggerFactory.getLogger(ProjectController.class);	
	
	public ProjectController(ProjectService service) {		
		this.service = service;		
	}

	@GetMapping
	public ResponseEntity< List<ProjectDto>> findAll() {       
		return ResponseEntity.ok(service.findAll()); 
	}   
	@GetMapping("/archived")
	public ResponseEntity< List<ProjectAuditDto>> findAllArchived() {       
		return ResponseEntity.ok(service.findAllArchived()); 
	}  
	
	@GetMapping("/project-key/{projectKey}")
	public ResponseEntity<ProjectDto> findByProjectKey(@PathVariable Integer projectKey){   	    	
		return   ResponseEntity.ok(service.findByProjectKey(projectKey));
						                
	}
	
	@GetMapping("/archived/project-key/{projectKey}")
	public ResponseEntity<ProjectAuditDto> findByProjectKeyArchived(@PathVariable Integer projectKey){ 
		return ResponseEntity.ok(service.findByProjectKeyArchived(projectKey));
	}
	
	@GetMapping("/exist")
	public boolean existsByTeamKey(@RequestParam Integer projectKey) {
		return service.existsByProjectKey(projectKey);
	}

	@PostMapping 
	public ResponseEntity<ProjectDto> create (@RequestBody @Valid ProjectCreateDto projectCreateDto, @RequestHeader("Authorization") String token ){
		logger.info("creating project ");
		UUID id = service.create(projectCreateDto, token);								
		
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PutMapping("/project-key/{projectKey}")
	public ResponseEntity<ProjectDto> update(@PathVariable Integer projectKey, @RequestBody ProjectUpdateDto projectUpdateDto,
											      @RequestHeader("Authorization") String token){
		logger.info("Updating project projectKey: {} in: {}", projectKey , projectUpdateDto);
		
		return ResponseEntity.ok(service.update(projectKey, projectUpdateDto, token));								
	}	
	
}
