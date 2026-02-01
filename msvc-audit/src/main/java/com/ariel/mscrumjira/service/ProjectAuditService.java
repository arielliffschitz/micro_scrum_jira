package com.ariel.mscrumjira.service;

import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;

public interface ProjectAuditService {

	void createProject( ProjectCreateAuditDto dto, String token);
	
	void createSprint(SprintCreateAuditDto dto, String token);
}
