package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;
public interface SprintBacklogItemService {

	List<SprintBacklogItemDto> findAll();   

	Optional<SprintBacklogItemDto> findByTaskNumber(Integer taskNumber);

	void save(SprintBacklogItemDto dto, String token);

	void deleteByTaskNumber(Integer taskNumber);

	SprintBacklogItemDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate,  String token);


}


