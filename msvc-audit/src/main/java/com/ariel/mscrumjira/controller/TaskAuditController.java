package com.ariel.mscrumjira.controller;

import java.util.List;

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

import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditDto;
import com.ariel.mscrumjira.service.TaskAuditService;
import com.ariel.mscrumjira.service.TaskMovementAuditService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskAuditController {
	
	private final TaskMovementAuditService movementTaskService;	
	private final TaskAuditService taskService;	
	private final Logger logger = LoggerFactory.getLogger(TaskAuditController.class);	
	
	
	public TaskAuditController(TaskMovementAuditService movementTaskService, TaskAuditService taskService) {		
		this.movementTaskService = movementTaskService;
		this.taskService = taskService;
	}

	@GetMapping("/movement/task-number/{taskNumber}")
	public ResponseEntity< List<TaskMovementAuditDto>> findMovementByTaskNumber(@PathVariable Integer taskNumber) {       
		return ResponseEntity.ok(movementTaskService.findByTaskNumber(taskNumber)); 
	} 
	
	@PostMapping ("/movement")
	public void createMovement(@RequestBody @Valid  TaskMovementAuditCreateDto dto ,
																@RequestHeader("Authorization") String token ){
		logger.info("creating taskAuditMovementState {} taskNumber {} ",dto.auditTaskState(),dto.taskNumber());
		movementTaskService.create(dto, token);			
	}
	
	@GetMapping("/task-number/{taskNumber}")
	public TaskAuditDto findByTaskNumber(@PathVariable Integer taskNumber) {       
		return   taskService.findByTaskNumber(taskNumber);				 
	} 
	
	@PostMapping 
	public void createTask(@RequestBody @Valid  TaskAuditCreateDto dto  ){
		logger.info("creating taskAudit with taskNumber {} ",dto.taskNumber());		
		taskService.create(dto);		
	}

}
