package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.BacklogItemDto;

public interface BacklogService {

    List<BacklogItemDto> findAll();

    Optional<BacklogItemDto> findById(Long id);

    BacklogItemDto save (BacklogItemDto backlogItemDto);

    void deleteById(Long id);

}
