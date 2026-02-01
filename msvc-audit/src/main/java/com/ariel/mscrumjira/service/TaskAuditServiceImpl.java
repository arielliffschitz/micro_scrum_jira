package com.ariel.mscrumjira.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;
import com.ariel.mscrumjira.entity.TaskAudit;
import com.ariel.mscrumjira.mapper.TaskAuditMapper;
import com.ariel.mscrumjira.repository.TaskAuditRepository;

@Service
public class TaskAuditServiceImpl implements TaskAuditService {

	final private TaskAuditRepository repository;
	
	
	public TaskAuditServiceImpl(TaskAuditRepository repository) {		
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public Optional<TaskAuditDto> findByTaskNumber(Integer taskNumber) {
		return	repository.findByTaskNumber(taskNumber).map(TaskAuditMapper::mapToTaskDto);
	}

	@Override
	public TaskAuditDto create(TaskAuditCreateDto createDto) {
		TaskAudit dao = TaskAuditMapper.mapToTaskDaoFromCreate(createDto);
		return  TaskAuditMapper.mapToTaskDto(repository.save(dao));
	}

}
