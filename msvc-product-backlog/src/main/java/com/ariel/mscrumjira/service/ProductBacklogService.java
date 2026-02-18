package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;

public interface ProductBacklogService {

    List<ProductBacklogItemDto> findAll();   

    ProductBacklogItemDto findByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto,  String token);  

    void deleteByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto findById(UUID id);

    public UUID create(ProductCreateDto backlogItemDto, String token);

    ProductBacklogItemDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate,  String token);
}
