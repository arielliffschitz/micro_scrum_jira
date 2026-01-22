package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.SprintState;
import com.ariel.mscrumjira.dto.SprintCreateDto;
import com.ariel.mscrumjira.dto.SprintDto;

public interface SprintService {
	
	List<SprintDto>findAll();		
	
	SprintDto findById(UUID id);
	
	Optional<SprintDto> findBySprintKey(Integer sprintKey);
	
	//List<SprintDto>findByProjectKey(Integer projectKey);
	
	UUID create(SprintCreateDto dto, String token);
	
	SprintDto updateState(Integer sprintKey, SprintState stateDto, String token);
	
	void deleteBySprintKey(Integer sprintKey);

}
