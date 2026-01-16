package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateDto;
public interface SprintBacklogItemService {

	List<SprintBacklogItemDto> findAll();   

	Optional<SprintBacklogItemDto>findByTaskNumber(Integer taskNumber);

	Optional<SprintBacklogItemDto> updateState(Integer taskNumber, TaskState taskState, @RequestHeader("Authorization") String token);

	SprintBacklogItemDto save(SprintBacklogItemDto dto, String token);

	void deleteByTaskNumber(Integer taskNumber);

	Optional<SprintBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate, @RequestHeader("Authorization") String token);


}


