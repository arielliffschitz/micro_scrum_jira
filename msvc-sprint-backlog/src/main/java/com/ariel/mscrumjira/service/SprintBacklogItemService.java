package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

public interface SprintBacklogItemService {

    List<SprintBacklogItemDto> findAll();

    Optional<SprintBacklogItemDto>findSprintById(UUID id);

    Optional<SprintBacklogItemDto>updateTaskState(UUID id, TaskState taskState);

    void deleteSprintById(UUID id);    

    SprintBacklogItemDto create(UUID  productBacklogId);
   
  
}
