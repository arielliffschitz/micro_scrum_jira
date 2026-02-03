package com.ariel.mscrumjira.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
	public TaskAuditDto findByTaskNumber(Integer taskNumber) {
		return	repository.findByTaskNumber(taskNumber).map(TaskAuditMapper::mapToTaskDto) 
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
	}

	@Override
	public void create(TaskAuditCreateDto createDto) {
		TaskAudit dao = TaskAuditMapper.mapToTaskDaoFromCreate(createDto);
		repository.save(dao);		
	}

}
