package com.ariel.mscrumjira.service;

import java.util.*;

import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;

public interface SprintService {
	
	List<SprintDto>findAll();		
	
	SprintDto findById(UUID id);
	
	SprintDto findBySprintKey(Integer sprintKey);
	
	boolean existsBySprintKey( Integer sprintKey);
	
	UUID create(SprintCreateDto dto, String token);
	
	SprintDto updateState(Integer sprintKey, SprintState stateDto, String token);	
	
	boolean existsByTeamKey(String teamKey);

}
