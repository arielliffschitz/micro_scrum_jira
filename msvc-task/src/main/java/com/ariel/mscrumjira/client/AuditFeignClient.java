package com.ariel.mscrumjira.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditDto;

import jakarta.validation.Valid;

@FeignClient(name = "msvc-audit", path = "/task")

public interface AuditFeignClient {	

	@GetMapping("/movement/task-number/{taskNumber}")
	 List<TaskMovementAuditDto> findMovementByTaskNumber(@PathVariable Integer taskNumber);    			
	
	@PostMapping("/movement")
	void createMovement(@RequestBody @Valid  TaskMovementAuditCreateDto dto ,@RequestHeader("Authorization") String token );
	
	@GetMapping("/task-number/{taskNumber}")
	public TaskAuditDto findByTaskNumber(@PathVariable Integer taskNumber);
	
	@PostMapping 
	public void createTask(@RequestBody @Valid  TaskAuditCreateDto dto  );
}

	
