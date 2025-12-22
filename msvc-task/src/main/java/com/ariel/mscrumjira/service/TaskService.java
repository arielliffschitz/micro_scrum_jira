package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

public interface TaskService {

    List<ProductBacklogItemDto> findAll();

    Optional<ProductBacklogItemDto> findTaskByTaskNumber(Integer taskNumber);   

    SprintBacklogItemDto moveFromProductToSprint(Integer taskNumber);

    ProductBacklogItemDto moveFromSprintToProduct(Integer taskNumber);   
}
