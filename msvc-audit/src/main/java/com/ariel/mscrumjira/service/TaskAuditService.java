package com.ariel.mscrumjira.service;

import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;

public interface TaskAuditService {

	TaskAuditDto findByTaskNumber(Integer taskNumber);
	
	void create(TaskAuditCreateDto dto);		
}
