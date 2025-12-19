package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
public interface SprintBacklogItemService {

    List<SprintBacklogItemDto> findAll();

    Optional<SprintBacklogItemDto> findById(UUID id);

    Optional<SprintBacklogItemDto> updateState(UUID id, TaskState taskState);

    SprintBacklogItemDto moveFromProduct(UUID productBacklogId);

    void moveBackToProduct(UUID sprintBacklogItemId);
}
  

