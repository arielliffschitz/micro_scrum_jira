package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.client.SprintFeignClient;
import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;
import com.ariel.mscrumjira.mapper.SprintBacklogItemMapper;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

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
