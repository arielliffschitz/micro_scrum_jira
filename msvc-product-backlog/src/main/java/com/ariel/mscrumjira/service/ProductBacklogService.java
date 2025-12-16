package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;

public interface ProductBacklogService {

    List<ProductBacklogItemDto> findAll();

    Optional<ProductBacklogItemDto> findById(UUID id);

    ProductBacklogItemDto save (ProductBacklogItemDto backlogItemDto);

    void deleteById(UUID id);

    ProductBacklogItemDto update(UUID id, ProductBacklogItemDto dtbacklogItemDtoo);

}
