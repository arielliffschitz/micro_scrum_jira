package com.ariel.mscrumjira.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;
import com.ariel.mscrumjira.service.SprintBacklogItemService;

@RestController
@RequestMapping("/sprint-backlog-items")
public class SprintBacklogItemController {   
	private final SprintBacklogItemService service;

	public SprintBacklogItemController(SprintBacklogItemService service) {
		this.service = service;
	}

	@PostMapping
	public void save (@RequestBody SprintBacklogItemDto dto, @RequestHeader("Authorization") String token) {         
		service.save(dto, token);
	}

	@GetMapping
	public List<SprintBacklogItemDto>list() {              
		return service.findAll();
	}    

	@GetMapping("/task-number/{taskNumber}")
	public SprintBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber)  {               
		return   service.findByTaskNumber(taskNumber)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));				                 
	} 

	@PutMapping("/task-number/{taskNumber}")
	public  SprintBacklogItemDto update(@PathVariable Integer taskNumber, @RequestBody UpdateSprintBacklogDto taskUpdate,
			@RequestHeader("Authorization") String token){
		return service.update(taskNumber, taskUpdate, token);  
	}

	@DeleteMapping("/task-number/{taskNumber}")
	public void deleteByTaskNumber(@PathVariable  Integer taskNumber)  {     
		service.deleteByTaskNumber( taskNumber);      
	}   
}
