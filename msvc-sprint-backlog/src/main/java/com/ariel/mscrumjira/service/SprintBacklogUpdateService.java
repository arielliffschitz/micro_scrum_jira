package com.ariel.mscrumjira.service;

import java.time.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.domain.entity.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;
import com.ariel.mscrumjira.repository.*;

@Service
public class SprintBacklogUpdateService {	

	private  SprintBacklogItemRepository repository;

	public SprintBacklogUpdateService(SprintBacklogItemRepository repository) {		
		this.repository = repository;
	}

	public SprintBacklogItemDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate, String token) {
		SprintBacklogItem dao = tryToFind(taskNumber);		                  
		SprintBacklogItemMapper.applyUpdateToSprint (dao, taskUpdate);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);
		
		if(taskUpdate.taskState()!=null)
			applyTransition(taskUpdate.taskState(), dao);
		
		return SprintBacklogItemMapper.mapToDto(repository.save(dao));
	}	
	
	private SprintBacklogItem tryToFind(Integer taskNumber) {
		return repository.findByTaskNumber(taskNumber)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SprintBacklogItem not found"));

	}
	
	private void applyTransition(TaskState next, SprintBacklogItem dao)  {
		if (dao.getTaskState().canTransitionTo(next) ){
			dao.setTaskState(next);
			takeAction(next, dao);
		}
		else { throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid transition ") ;}

	}		 

	private void takeAction(TaskState next, SprintBacklogItem dao) {        
		switch (next) {
		case TaskState.IN_PROGRESS : dao.setStartDate(LocalDateTime.now());                
		break;
		case TaskState.DONE : dao.setEndDate(LocalDateTime.now());                
		break;
		case TaskState.BLOCKED : //TODO message to boss ");                
		break;
		default:
			break;
		} 
	}

}
