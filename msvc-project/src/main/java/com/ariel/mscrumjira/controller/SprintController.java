package com.ariel.mscrumjira.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.domain.enums.SprintState;
import com.ariel.mscrumjira.dto.SprintCreateDto;
import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.service.SprintService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sprint")
public class SprintController {

	final private SprintService service;
	private final Logger logger = LoggerFactory.getLogger(SprintController.class);
	
	public SprintController(SprintService service) {		
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity< List<SprintDto>> findAll() {       
		return ResponseEntity.ok(service.findAll()); 
	}   
	
	@GetMapping("/sprint-key/{sprintKey}")
	public ResponseEntity<SprintDto> findBySprintKey(@PathVariable Integer sprintKey){   	    	
		return   service.findBySprintKey( sprintKey)
				.map(dto->ResponseEntity.ok(dto) )
				.orElseGet(()->ResponseEntity.notFound().build());			                
	}

	@GetMapping("/exist")
	public boolean existsBySprintKey(@RequestParam Integer sprintKey) {
		return service.existsBySprintKey(sprintKey);
	}
	
	@GetMapping("/exist-team")
	public boolean existsByTeamKey(@RequestParam String teamKey) {
		return service.existsByTeamKey(teamKey);
	}
	
	@PostMapping 
	public ResponseEntity<SprintDto> create (@RequestBody @Valid SprintCreateDto sprintCreateDto, @RequestHeader("Authorization") String token ){
		logger.info("creating Sprint ");
		UUID id = service.create(sprintCreateDto, token);								
		
		return ResponseEntity.ok(service.findById(id));
	}
	@PutMapping("/sprint-key/{sprintKey}")
	public ResponseEntity<SprintDto> updateState (@PathVariable Integer sprintKey,@RequestBody SprintState state, @RequestHeader("Authorization") String token ){
		return ResponseEntity.ok(service.updateState(sprintKey, state, token));
	}
	
	@DeleteMapping ("/sprint-key/{sprintKey}")
	public ResponseEntity<Void> delete (@PathVariable Integer sprintKey){
		service.deleteBySprintKey(sprintKey);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
