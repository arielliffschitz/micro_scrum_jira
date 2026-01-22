package com.ariel.scrumjira.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.scrumjira.dto.TeamCreateDto;
import com.ariel.scrumjira.dto.TeamDto;
import com.ariel.scrumjira.service.TeamService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/team")
public class TeamController {
	
	final private TeamService service;
	private final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	
	public TeamController(TeamService service) {		
		this.service = service;
	}

	@GetMapping
	public ResponseEntity< List<TeamDto>> findAll() {       
		return ResponseEntity.ok(service.findAll()); 
	}   
	
	@GetMapping("/team-key/{teamKey}")
	public ResponseEntity <List<TeamDto>> findByTeamKey(@PathVariable String teamKey){   	    	
		return   ResponseEntity.ok(service.findByTeamKey(teamKey));
				                
	}
	@GetMapping("/exist")
	public boolean existsByTeamKey(@RequestParam String teamKey) {
		return service.existsByTeamKey(teamKey);
	}
	
	@PostMapping 
	public ResponseEntity<TeamDto> create (@RequestBody @Valid TeamCreateDto teamCreateDto){//, @RequestHeader("Authorization") String token ){
		logger.info("creating user {} in the team {} ", teamCreateDto.username(), teamCreateDto.teamKey());
		UUID id = service.create(teamCreateDto, "token");								
		
		return ResponseEntity.ok(service.findById(id));
	}

}
