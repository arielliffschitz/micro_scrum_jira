package com.ariel.mscrumjira.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.client.AuditFeignClient;
import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.client.SprintBacklogFeignClient;
import com.ariel.mscrumjira.domain.enums.AuditTaskState;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.TaskMoveSprintRequestDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.mapper.TaskItemMapper;

import feign.FeignException;
@Service
public class TaskServiceImpl implements TaskService{    

	private final ProductBacklogFeignClient clientProduct;
	private final SprintBacklogFeignClient  clientSprint;  
	private final AuditFeignClient  clientAudit;	

	public TaskServiceImpl(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint,
			AuditFeignClient clientAudit) {		
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
		this.clientAudit = clientAudit;
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
		TaskDto taskDto = tryFindInSprint(taskNumber);
		if (taskDto == null)
			taskDto = tryFindInProduct(taskNumber);
		if (taskDto == null)
			taskDto = tryFindInAudit(taskNumber);
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
		ProductBacklogItemDto productDto;
		try {
			productDto = clientProduct.findByTaskNumber(dto.taskNumber());
		} catch (FeignException.NotFound e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found inProduct");
		}
		SprintBacklogItemDto  sprintDto  = TaskItemMapper.mapFromProductDtoToSprintDto(productDto);
		sprintDto.setSprintKey(dto.sprintKey());
		clientSprint.save(sprintDto, token);

		clientProduct.deleteProductByTaskNumber(dto.taskNumber());

		clientAudit.createMovement(new TaskMovementAuditCreateDto(dto.taskNumber(), AuditTaskState.MOVE_TO_SPRINT), token);

		return TaskItemMapper.toTaskDtoFromSprint(sprintDto);
	}

	@Override
	public TaskDto moveFromSprintToProduct(Integer taskNumber,  String token) {
		SprintBacklogItemDto sprintDto;
		try {
		    sprintDto = clientSprint.findByTaskNumber(taskNumber);
		} catch (FeignException.NotFound e) {
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found in Sprint");
		}
		ProductBacklogItemDto productDto = clientProduct.save(TaskItemMapper.mapFromSprintDtoToProductDto(sprintDto), token);
		
		clientSprint.deleteProductByTaskNumber(taskNumber);
		
		clientAudit.createMovement(new TaskMovementAuditCreateDto(productDto.getTaskNumber(), AuditTaskState.MOVE_TO_PRODUCT), token);
		
		return TaskItemMapper.toTaskDtoFromProduct(productDto);
	}

	@Override
	public TaskDto update(Integer taskNumber, UpdateDto taskUpdate,  String token) {  
		TaskDto taskDto = tryFindInSprint(taskNumber); 
		if (taskDto != null) {
			clientSprint.update(taskNumber, taskUpdate,token);
			return taskDto;
		}
		taskDto = tryFindInProduct(taskNumber);						
		if (taskDto != null) {clientProduct.update(taskNumber, taskUpdate, token);
		return taskDto;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
	}   

	@Override
	public TaskDto updateState(Integer taskNumber, TaskState taskState,  String token) {
		TaskDto taskDto =TaskItemMapper.toTaskDtoFromSprint(clientSprint.updateState(taskNumber, taskState, token));
		clientAudit.createMovement(new TaskMovementAuditCreateDto(taskNumber,TaskItemMapper.fromTaskStateToAuditTaskState(taskState)), token);
		
		if (taskDto.getTaskState().equals(TaskState.ARCHIVED)) {
			clientAudit.createTask(TaskItemMapper.toTaskCreateDtoFromTaskDto(taskDto));
			clientSprint.deleteProductByTaskNumber(taskNumber);
		}
		return taskDto;
	}   
	private TaskDto tryFindInSprint(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
			taskDto = TaskItemMapper.toTaskDtoFromSprint(clientSprint.findByTaskNumber(taskNumber));
		} catch (FeignException.NotFound e) {	        
		}
		return taskDto;
	}
	private TaskDto tryFindInProduct(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
			taskDto = TaskItemMapper.toTaskDtoFromProduct(clientProduct.findByTaskNumber(taskNumber));
		} catch (FeignException.NotFound e) {	            
		}
		return taskDto;
	}
	private TaskDto tryFindInAudit(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
            taskDto = TaskItemMapper.toTaskDtoFromAudit(clientAudit.findByTaskNumber(taskNumber));
       } catch (FeignException.NotFound e) {	            
       }
		return taskDto;
	}	
	
}
