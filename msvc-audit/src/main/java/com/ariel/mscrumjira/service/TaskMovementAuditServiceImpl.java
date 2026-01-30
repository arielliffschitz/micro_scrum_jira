package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.dto.TaskMovementAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditDto;
import com.ariel.mscrumjira.entity.TaskMovementAudit;
import com.ariel.mscrumjira.mapper.TaskAuditMapper;
import com.ariel.mscrumjira.repository.TaskMovementAuditRepository;

@Service
public class TaskMovementAuditServiceImpl implements TaskMovementAuditService {
	
	private final TaskMovementAuditRepository repository;		

	public TaskMovementAuditServiceImpl(TaskMovementAuditRepository repository) {		
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TaskMovementAuditDto> findByTaskNumber(Integer taskNumber) {
		return repository.findByTaskNumber(taskNumber).stream()				               
				.map(TaskAuditMapper::mapToMovementDto)
				.collect(Collectors.toList());	
	}

	@Override
	@Transactional
	public TaskMovementAuditDto create(TaskMovementAuditCreateDto dto, String token) {
		TaskMovementAudit dao =  new TaskMovementAudit(dto.taskNumber(), dto.auditTaskState());						
		AuditUtil.BaseEntityCreatedFields(dao, token);

		return  TaskAuditMapper.mapToMovementDto(repository.save(dao));
	}
}
