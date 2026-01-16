package com.ariel.mscrumjira.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.client.SprintBacklogFeignClient;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.mapper.TaskItemMapper;

import feign.FeignException;
@Service
public class TaskServiceImpl implements TaskService{    

	private final ProductBacklogFeignClient clientProduct;
	private final SprintBacklogFeignClient  clientSprint;        


	public TaskServiceImpl(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint) {
		this.clientProduct = clientProduct;
		this.clientSprint = clientSprint;
	}

	@Override
	public List<TaskDto> findAll() {
		List<TaskDto> taskProductDtoList = StreamSupport.stream(clientSprint.findAll()
				.spliterator(),false)
				.map(TaskItemMapper::toTaskDtoFromSprint)
				.collect(Collectors.toList()); 
		List<TaskDto> taskSprintDtoList = StreamSupport.stream(clientProduct.findAll()
				.spliterator(),false)
				.map(TaskItemMapper::toTaskDtoFromProduct)
				.collect(Collectors.toList()); 

		List<TaskDto> taskDtoList= Stream.concat(taskProductDtoList.stream(), taskSprintDtoList.stream()).toList();

		return  taskDtoList.stream().sorted(Comparator.comparing(TaskDto::taskNumber).reversed()).toList();                                   
	}    

	@Override
	public Optional<TaskDto> findByTaskNumber(Integer taskNumber) {
		try {
			SprintBacklogItemDto  sprintDto = clientSprint.findByTaskNumber(taskNumber);
			return Optional.of(TaskItemMapper.toTaskDtoFromSprint(sprintDto));
		} catch (FeignException.NotFound e) {
			return Optional.ofNullable(TaskItemMapper.toTaskDtoFromProduct(clientProduct.findByTaskNumber(taskNumber)));
		}      
	}    
	@Override
	public TaskDto create(ProductCreateDto dto, @RequestHeader("Authorization") String token ) {
		return TaskItemMapper.toTaskDtoFromProduct(clientProduct.create(dto, token));
	}

	@Override
	public TaskDto moveFromProductToSprint(Integer taskNumber, String token) {
		ProductBacklogItemDto productDto = clientProduct.findByTaskNumber(taskNumber);
		SprintBacklogItemDto  sprintDto  = clientSprint.save(TaskItemMapper.mapFromProductDtoToSprintDto(productDto), token);
		clientProduct.deleteProductByTaskNumber(taskNumber);
		return TaskItemMapper.toTaskDtoFromSprint(sprintDto);
	}

	@Override
	public TaskDto moveFromSprintToProduct(Integer taskNumber, @RequestHeader("Authorization") String token) {
		SprintBacklogItemDto  SprintDto  = clientSprint.findByTaskNumber(taskNumber);
		ProductBacklogItemDto productDto = clientProduct.save(TaskItemMapper.mapFromSprintDtoToProductDto(SprintDto), token);
		clientSprint.deleteProductByTaskNumber(taskNumber);
		return TaskItemMapper.toTaskDtoFromProduct(productDto);
	}

	@Override
	public TaskDto update(Integer taskNumber, UpdateDto taskUpdate, @RequestHeader("Authorization") String token) {      
		try {
			clientSprint.findByTaskNumber(taskNumber);                                                
			return TaskItemMapper.toTaskDtoFromSprint(clientSprint.update(taskNumber, taskUpdate,token));

		} catch (FeignException.NotFound e) {            
			return TaskItemMapper.toTaskDtoFromProduct(clientProduct.update(taskNumber, taskUpdate, token));
		}                                             
	}   

	@Override
	public TaskDto updateState(Integer taskNumber, TaskState taskState, @RequestHeader("Authorization") String token) {
		return TaskItemMapper.toTaskDtoFromSprint(clientSprint.updateState(taskNumber, taskState, token));
	}            
}
