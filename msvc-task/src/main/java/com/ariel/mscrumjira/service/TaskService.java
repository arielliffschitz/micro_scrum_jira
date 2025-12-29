package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public interface TaskService {    

    List<TaskDto> findAll();

    Optional<TaskDto> findByTaskNumber(Integer taskNumber);   

    TaskDto moveFromProductToSprint(Integer taskNumber);

    TaskDto moveFromSprintToProduct(Integer taskNumber);   

    TaskDto update(Integer taskNumber, UpdateDto dto);   

    TaskDto updateState (Integer taskNumber,   TaskState taskState);

    TaskDto create(ProductCreateDto dto);
}
