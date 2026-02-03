package com.ariel.mscrumjira.service;

import java.util.List;

import com.ariel.mscrumjira.dto.ProjectAuditDto;
import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;

public interface ProjectAuditService {

	void createProject( ProjectCreateAuditDto dto, String token);
	
	void createSprint(SprintCreateAuditDto dto, String token);
	
	List<ProjectAuditDto> findAll();
	
	ProjectAuditDto findByProjectKey(Integer projectKey);
}
