package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;

public interface ProductBacklogService {

    List<ProductBacklogItemDto> findAll();   

    Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto);  

    void deleteByTaskNumber(Integer taskNumber);

    ProductBacklogItemDto update(Integer taskNumber, ProductBacklogItemDto backlogItemDto);

}
