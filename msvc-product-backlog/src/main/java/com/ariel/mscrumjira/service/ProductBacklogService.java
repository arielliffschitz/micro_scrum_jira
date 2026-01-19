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

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto,  String token);  

    void deleteByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto findById(UUID id);

    public UUID create(ProductCreateDto backlogItemDto, String token);

    Optional<ProductBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate,  String token);
}
