package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;

public interface TaskService {    

    List<TaskDto> findAll();

    Optional<TaskDto> findByTaskNumber(Integer taskNumber);   

    SprintBacklogItemDto moveFromProductToSprint(Integer taskNumber);

    ProductBacklogItemDto moveFromSprintToProduct(Integer taskNumber);   
}
