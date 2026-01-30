package com.ariel.mscrumjira.service;

import java.util.Optional;

import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;

public interface TaskAuditService {

	Optional<TaskAuditDto> findByTaskNumber(Integer taskNumber);
	
	TaskAuditDto create(TaskAuditCreateDto dto);		
}
