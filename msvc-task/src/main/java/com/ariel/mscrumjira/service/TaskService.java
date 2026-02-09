package com.ariel.mscrumjira.service;

import java.util.List;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.TaskMoveSprintRequestDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;


public interface TaskService {    

	List<TaskDto> findAll();

	TaskDto findByTaskNumber(Integer taskNumber);   

	TaskDto moveFromProductToSprint(TaskMoveSprintRequestDto  dto, String token);

	TaskDto moveFromSprintToProduct(Integer taskNumber,  String token);   

	TaskDto update(Integer taskNumber, UpdateSprintBacklogDto dto,  String token);   

	TaskDto updateState (Integer taskNumber,   TaskState taskState,  String token);

	TaskDto create(ProductCreateDto dto,   String token);
}
