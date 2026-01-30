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
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.mapper.SprintBacklogItemMapper;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {

	private final SprintBacklogItemRepository repository;
	private final SprintFeignClient sprintClient;
	
	private static final Map<TaskState, Set<TaskState>> TASK_STATE_VALID = Map.of(
						TaskState.PENDING,    Set.of(TaskState.IN_PROGRESS, TaskState.BLOCKED,TaskState.ARCHIVED),
			            TaskState.IN_PROGRESS,Set.of(TaskState.DONE, TaskState.BLOCKED,TaskState.ARCHIVED) ,
			            TaskState.BLOCKED,    Set.of(TaskState.IN_PROGRESS,TaskState.ARCHIVED),
			            TaskState.DONE,       Set.of(TaskState.ARCHIVED)
           ); 

	

	public SprintBacklogItemServiceImpl(SprintBacklogItemRepository repository, SprintFeignClient sprintClient) {
		super();
		this.repository = repository;
		this.sprintClient = sprintClient;
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
	public SprintBacklogItemDto save(SprintBacklogItemDto dto, String token) {
		if(!sprintClient.existsBySprintKey(dto.getSprintKey())) { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the sprintKey: " + dto.getSprintKey() + " doesn't exist");}
		SprintBacklogItem dao = SprintBacklogItemMapper.mapToDao(dto);
		dao.setTaskState(TaskState.PENDING); 
		AuditUtil.BaseEntityUpdateFields(dao, token);		
		
		return SprintBacklogItemMapper.mapToDto(repository.save(dao));        
	}	

	@Override
	@Transactional
	public Optional<SprintBacklogItemDto> updateState(Integer taskNumber, TaskState taskState, String token) {		
		return repository.findByTaskNumber(taskNumber)
				.map(dao -> {                    
					applyTransition(taskState, dao);
					AuditUtil.BaseEntityUpdateFields(dao, token);
					return SprintBacklogItemMapper.mapToDto(repository.save(dao));
				});
	}  
	
	private void applyTransition(TaskState next, SprintBacklogItem dao)  {
	       if (TASK_STATE_VALID.get(dao.getTaskState()).contains(next) ){
	    	   dao.setTaskState(next);
	    	   takeAction(next, dao);
	       }
	       else { throw new RuntimeException("Invalid transition ") ;}
	       
	 }		 
	 
	 private void takeAction(TaskState next, SprintBacklogItem dao) {        
	        switch (next) {
	            case TaskState.IN_PROGRESS : dao.setStartDate(LocalDateTime.now());                
	                break;
	            case TaskState.DONE : dao.setEndDate(LocalDateTime.now());                
	                break;
	            case TaskState.BLOCKED : System.out.println("TODO message to boss ");                
	                break;
	            default:
	                break;
	        } 
	 }
	
//	//@Override
//	@Transactional
//	public Optional<SprintBacklogItemDto> updateState2(Integer taskNumber, TaskState taskState, String token) {		
//		return repository.findByTaskNumber(taskNumber)
//				.map(dao -> {                    
//					dao = SprintBacklogItemMapper.mapToDaoUpdate(stateService.applyTransition(taskState, SprintBacklogItemMapper.mapToDtoUpdate(dao)));
//					AuditUtil.BaseEntityUpdateFields(dao, token);
//					return SprintBacklogItemMapper.mapToDto(repository.save(dao));
//				});
//	}  

	@Override
	@Transactional
	public void deleteByTaskNumber(Integer taskNumber) {
		repository.deleteByTaskNumber(taskNumber);
	}

	@Override
	@Transactional
	public Optional<SprintBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate, String token) {

		return repository.findByTaskNumber(taskNumber)
				.map(dao -> {                    
					SprintBacklogItemMapper.applyUpdateToSprint (dao, taskUpdate);
					AuditUtil.BaseEntityUpdateFields(dao, token);
					return SprintBacklogItemMapper.mapToDto(repository.save(dao));
				});
	}



}
