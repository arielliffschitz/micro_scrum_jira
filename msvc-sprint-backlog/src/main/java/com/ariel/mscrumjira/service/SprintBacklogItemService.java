package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
public interface SprintBacklogItemService {

    List<SprintBacklogItemDto> findAll();   

    Optional<SprintBacklogItemDto>findByTaskNumber(Integer taskNumber);

    Optional<SprintBacklogItemDto> updateState(Integer taskNumber, TaskState taskState);

    SprintBacklogItemDto save(SprintBacklogItemDto dto);

    void deleteByTaskNumber(Integer taskNumber);

   
}
  

