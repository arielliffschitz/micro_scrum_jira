package com.ariel.mscrumjira.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;

@Service
public class TaskServiceImpl implements TaskService{    

	private  ProductBacklogFeignClient clientProduct;
	private  SprintBacklogFeignClient  clientSprint;  
	private  AuditFeignClient  clientAudit;	
	private TaskFinderService finderService;	
	private TaskMoveService  moveService;	
	private TaskUpdateService updateService;

	
	public TaskServiceImpl(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint,
			AuditFeignClient clientAudit, TaskFinderService finderService, TaskMoveService moveService,
			TaskUpdateService updateService) {		
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
		this.clientAudit = clientAudit;
		this.finderService = finderService;
		this.moveService = moveService;
		this.updateService = updateService;
	}

	@Override	
	public List<TaskDto> findAll() {
		List<TaskDto> taskProductDtoList = (clientSprint.findAll().stream()				
				.map(TaskItemMapper::toTaskDtoFromSprint)
				.collect(Collectors.toList())); 
		List<TaskDto> taskSprintDtoList = (clientProduct.findAll().stream()				
				.map(TaskItemMapper::toTaskDtoFromProduct)
				.collect(Collectors.toList())); 

		List<TaskDto> taskDtoList= Stream.concat(taskProductDtoList.stream(), taskSprintDtoList.stream()).toList();

		return  taskDtoList.stream().sorted(Comparator.comparing(TaskDto::getTaskNumber).reversed()).toList();                                   
	}    

	@Override
	public TaskDto findByTaskNumber(Integer taskNumber) {
		TaskDto taskDto = finderService.tryFindInSprint(taskNumber);
		if (taskDto == null)
			taskDto = finderService.tryFindInProduct(taskNumber);
		if (taskDto == null)
			taskDto = finderService.tryFindInAudit(taskNumber);
		if (taskDto != null) {
			taskDto.setTaskMovements(clientAudit.findMovementByTaskNumber(taskNumber));
			return taskDto;	
		} 
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
	}

	@Override
	public TaskDto create(ProductCreateDto dto,  String token ) {
		return TaskItemMapper.toTaskDtoFromProduct(clientProduct.create(dto, token));
	}

	@Override
	public TaskDto moveFromProductToSprint(TaskMoveSprintRequestDto  dto, String token) {			
		return TaskItemMapper.toTaskDtoFromSprint(moveService.moveFromProductToSprint(dto, token));
	}		

	@Override
	public TaskDto moveFromSprintToProduct(Integer taskNumber,  String token) {					
		return TaskItemMapper.toTaskDtoFromProduct(moveService.moveFromSprintToProduct(taskNumber, token));
	}	

	@Override
	public TaskDto update(Integer taskNumber, UpdateDto taskUpdate,  String token) { 		
		return updateService.update(taskNumber, taskUpdate, token);			
	}   

	@Override
	public TaskDto updateState(Integer taskNumber, TaskState taskState,  String token) {
		return updateService.updateState(taskNumber, taskState, token);
	}	
}
