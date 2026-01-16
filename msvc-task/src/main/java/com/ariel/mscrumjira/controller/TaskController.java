package com.ariel.mscrumjira.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.service.TaskService;

import jakarta.validation.Valid;

@RestController
public class TaskController {

	private final TaskService service;
	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	public TaskController(TaskService service) {
		this.service = service;
	}    
	@GetMapping
	public ResponseEntity<List<TaskDto>> findAll( ) {    	
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/task-number/{taskNumber}")
	public ResponseEntity<TaskDto> findByTaskNumber(@PathVariable Integer taskNumber){                          
		return  service.findByTaskNumber(taskNumber)
				.map(dto->ResponseEntity.ok(dto) )
				.orElseGet(()->ResponseEntity.notFound().build());
	}

	@PostMapping("/product-to-sprint/{taskNumber}")
	public ResponseEntity<TaskDto> moveFromProductToSprint(@PathVariable Integer taskNumber, 
			@RequestHeader("Authorization") String token) {
		TaskDto result = service.moveFromProductToSprint(taskNumber, token);
		logger.info("Task moved Product→Sprint: taskNumber={}", taskNumber);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/sprint-to-product/{taskNumber}")
	public ResponseEntity<TaskDto> moveFromSprintToProduct(@PathVariable Integer taskNumber, @RequestHeader("Authorization") String token) {
		TaskDto result = service.moveFromSprintToProduct(taskNumber, token);
		logger.info("Task moved Sprint→Product: taskNumber={}", taskNumber);
		return ResponseEntity.ok(result);
	}    

	@PutMapping("/{taskNumber}/state/{taskState}")
	public ResponseEntity<TaskDto> updateState(@PathVariable Integer taskNumber,  @PathVariable TaskState taskState, 
			@RequestHeader("Authorization") String token) {
		logger.info("Updating stated SprintBacklogItem taskNumber: {} in: {}",taskNumber , taskState);
		return ResponseEntity.ok(service.updateState(taskNumber, taskState, token));                      
	}  

	@PostMapping 
	public ResponseEntity<TaskDto> create(@RequestBody @Valid ProductCreateDto dto, @RequestHeader("Authorization") String token){
		logger.info("creating item ");
		return ResponseEntity.ok(service.create(dto, token));
	}

	@PutMapping("/task-number/{taskNumber}")
	public ResponseEntity<TaskDto> update(@PathVariable Integer taskNumber,  @RequestBody UpdateDto updateDto,
			@RequestHeader("Authorization") String token) {
		logger.info("Updating task taskNumber: {} in: {}",taskNumber , updateDto);
		return ResponseEntity.ok(service.update(taskNumber, updateDto, token));                      
	}  
}
