package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public interface ProductBacklogService {

    List<ProductBacklogItemDto> findAll();   

    Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto);  

    void deleteByTaskNumber(Integer taskNumber);

    Optional<ProductBacklogItemDto> findById(UUID id);

    public UUID create(ProductCreateDto backlogItemDto);

    Optional<ProductBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate);
}
