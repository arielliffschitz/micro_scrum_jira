package com.ariel.mscrumjira.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;
import com.ariel.mscrumjira.mapper.*;
import com.ariel.mscrumjira.repository.*;

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
