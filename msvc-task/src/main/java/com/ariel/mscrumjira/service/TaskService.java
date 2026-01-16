package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public interface TaskService {    

	List<TaskDto> findAll();

	Optional<TaskDto> findByTaskNumber(Integer taskNumber);   

	TaskDto moveFromProductToSprint(Integer taskNumber, String token);

	TaskDto moveFromSprintToProduct(Integer taskNumber, @RequestHeader("Authorization") String token);   

	TaskDto update(Integer taskNumber, UpdateDto dto, @RequestHeader("Authorization") String token);   

	TaskDto updateState (Integer taskNumber,   TaskState taskState, @RequestHeader("Authorization") String token);

	TaskDto create(ProductCreateDto dto,  @RequestHeader("Authorization") String token);
}
