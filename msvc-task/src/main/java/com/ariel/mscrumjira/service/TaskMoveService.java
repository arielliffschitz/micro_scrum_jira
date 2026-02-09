package com.ariel.mscrumjira.service;

import org.springframework.stereotype.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;

@Service
public class TaskMoveService {
	
	private  ProductBacklogFeignClient clientProduct;
	private  SprintBacklogFeignClient  clientSprint;  
	private  AuditFeignClient  clientAudit;	
	private TaskFinderService finderService;
	
	
	public TaskMoveService(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint,
			AuditFeignClient clientAudit, TaskFinderService finderService) {	
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
		this.clientAudit = clientAudit;
		this.finderService = finderService;
	}


	public SprintBacklogItemDto moveFromProductToSprint(TaskMoveSprintRequestDto  dto, String token) {
		ProductBacklogItemDto productDto = finderService.findInProduct(dto.taskNumber());	
		
		SprintBacklogItemDto  sprintDto  =TaskItemMapper.toSprintDtoFromProductDto(productDto);
		sprintDto.setSprintKey(dto.sprintKey());
		clientSprint.save(sprintDto, token);

		clientProduct.deleteProductByTaskNumber(dto.taskNumber());

		clientAudit.createMovement(new TaskMovementAuditCreateDto(dto.taskNumber(), AuditTaskState.MOVE_TO_SPRINT), token);

		return sprintDto;
	}	
	
	public ProductBacklogItemDto moveFromSprintToProduct(Integer taskNumber,  String token) {
		SprintBacklogItemDto sprintDto = finderService.findInSprint(taskNumber);
		
		ProductBacklogItemDto productDto = clientProduct.save(TaskItemMapper.toProductDtoFromSprintDto(sprintDto), token);
		
		clientSprint.deleteByTaskNumber(taskNumber);
		
		clientAudit.createMovement(new TaskMovementAuditCreateDto(productDto.getTaskNumber(), AuditTaskState.MOVE_TO_PRODUCT), token);
		
		return productDto;
	}	

}
