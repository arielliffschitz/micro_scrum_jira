package com.ariel.mscrumjira.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.entity.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;
import com.ariel.mscrumjira.repository.*;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {

	private  SprintBacklogItemRepository repository;
	private  SprintFeignClient sprintClient;
	private SprintBacklogUpdateService sprintUpdateService;	

	public SprintBacklogItemServiceImpl(SprintBacklogItemRepository repository, SprintFeignClient sprintClient,
			SprintBacklogUpdateService sprintUpdateService) {		
		this.repository = repository;
		this.sprintClient = sprintClient;
		this.sprintUpdateService = sprintUpdateService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SprintBacklogItemDto> findAll() {
		return repository.findAll().stream()				
				.map(SprintBacklogItemMapper::mapToDto)
				.collect(Collectors.toList());
	}  

	@Override
	@Transactional(readOnly = true)
	public Optional <SprintBacklogItemDto> findByTaskNumber(Integer taskNumber) {			
		return repository.findByTaskNumber(taskNumber) .map(SprintBacklogItemMapper::mapToDto);                
	}     

	@Override
	@Transactional
	public void save(SprintBacklogItemDto dto, String token) {
		validateSprintKey(dto.getSprintKey());		
		SprintBacklogItem dao = SprintBacklogItemMapper.mapToDao(dto);
		dao.setTaskState(TaskState.PENDING); 
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);		
		repository.save(dao);		        
	}	

	@Override
	@Transactional
	public void deleteByTaskNumber(Integer taskNumber) {
		repository.deleteByTaskNumber(taskNumber);
	}

	@Override
	@Transactional
	public SprintBacklogItemDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate, String token) {		
		return sprintUpdateService.update(taskNumber, taskUpdate, token);
	}	
	
	private void validateSprintKey(Integer sprintKey) {
		if(!sprintClient.existsBySprintKey(sprintKey)) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the sprintKey: " + sprintKey + " doesn't exist");}		
	}

}
