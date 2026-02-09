package com.ariel.mscrumjira.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;

@Service
public class TaskUpdateService {
	
	private  ProductBacklogFeignClient clientProduct;
	private  SprintBacklogFeignClient  clientSprint;  
	private  AuditFeignClient  clientAudit;	
	private  TaskFinderService finderService;	
	
	public TaskUpdateService(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint,
			AuditFeignClient clientAudit, TaskFinderService finderService) {		
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
		this.clientAudit = clientAudit;
		this.finderService = finderService;
	}

	public TaskDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate,  String token) {  
		TaskDto taskDto = finderService.tryFindInSprint(taskNumber); 
		if (taskDto != null) {
			clientSprint.update(taskNumber, taskUpdate,token);
			return taskDto;
		}
		taskDto = finderService.tryFindInProduct(taskNumber);						
		if (taskDto != null) {
			clientProduct.update(taskNumber, taskUpdate, token);
			return taskDto;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
	}  
	
	public TaskDto updateState(Integer taskNumber, TaskState taskState,  String token) {
		TaskDto taskDto =TaskItemMapper.toTaskDtoFromSprint(clientSprint.update(taskNumber, new UpdateSprintBacklogDto(taskState), token));
		clientAudit.createMovement(new TaskMovementAuditCreateDto(taskNumber,TaskItemMapper.toAuditTaskStateFromTaskState(taskState)), token);
		
		if (taskDto.getTaskState().equals(TaskState.ARCHIVED)) {
			clientAudit.createTask(TaskItemMapper.toTaskCreateDtoFromTaskDto(taskDto));
			clientSprint.deleteByTaskNumber(taskNumber);
		}
		return taskDto;
	}		
}
