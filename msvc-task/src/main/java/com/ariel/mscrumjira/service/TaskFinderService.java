package com.ariel.mscrumjira.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;

import feign.*;

@Service
public class TaskFinderService {
	
	private  ProductBacklogFeignClient clientProduct;
	private  SprintBacklogFeignClient  clientSprint;  
	private  AuditFeignClient  clientAudit;	

	public TaskFinderService(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint,
							AuditFeignClient clientAudit) {		
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
		this.clientAudit = clientAudit;
	}
	
	public TaskDto tryFindInSprint(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
			taskDto = TaskItemMapper.toTaskDtoFromSprint(clientSprint.findByTaskNumber(taskNumber));
		} catch (FeignException.NotFound e) {	        
		}
		return taskDto;
	}
	public TaskDto tryFindInProduct(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
			taskDto = TaskItemMapper.toTaskDtoFromProduct(clientProduct.findByTaskNumber(taskNumber));
		} catch (FeignException.NotFound e) {	            
		}
		return taskDto;
	}
	public TaskDto tryFindInAudit(Integer taskNumber) {
		TaskDto taskDto = null;
		try {
            taskDto = TaskItemMapper.toTaskDtoFromAudit(clientAudit.findByTaskNumber(taskNumber));
       } catch (FeignException.NotFound e) {	            
       }
		return taskDto;
	}	
	public ProductBacklogItemDto findInProduct( Integer taskNumber) {
		try {
			return clientProduct.findByTaskNumber(taskNumber);
		} catch (FeignException.NotFound e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found inProduct");
		}		
	}	
	public SprintBacklogItemDto findInSprint(Integer taskNumber) {
		try {
			return clientSprint.findByTaskNumber(taskNumber);
		} catch (FeignException.NotFound e) {
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found in Sprint");
		}		
	}
}
