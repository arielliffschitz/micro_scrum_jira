package com.ariel.mscrumjira.service;

import java.util.List;

import com.ariel.mscrumjira.dto.TaskMovementAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditDto;

public interface TaskMovementAuditService {

	List<TaskMovementAuditDto>findByTaskNumber(Integer taskNumber);
	
	void create(TaskMovementAuditCreateDto dto,  String token);		
}
