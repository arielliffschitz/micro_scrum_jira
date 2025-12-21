package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

public interface TaskService {

    List<SprintBacklogItemDto> findAll();

    Optional<ProductBacklogItemDto> findProductByTaskNumber(Integer taskNumber);

    public Optional<SprintBacklogItemDto> findSprintByTaskNumber(Integer taskNumber);

    SprintBacklogItemDto moveToSprint(Integer taskNumber);

    void moveToProduct(Integer taskNumber);

    ProductBacklogItemDto test(Integer taskNumber);
}
