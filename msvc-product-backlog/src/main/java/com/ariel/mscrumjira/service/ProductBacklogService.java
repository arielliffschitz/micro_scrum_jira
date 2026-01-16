package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public interface ProductBacklogService {

    List<ProductBacklogItemDto> findAll();   

    Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto, @RequestHeader("Authorization") String token);  

    void deleteByTaskNumber(Integer taskNumber);

    Optional<ProductBacklogItemDto> findById(UUID id);

    public UUID create(ProductCreateDto backlogItemDto, @RequestHeader("Authorization") String token);

    Optional<ProductBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate, @RequestHeader("Authorization") String token);
}
