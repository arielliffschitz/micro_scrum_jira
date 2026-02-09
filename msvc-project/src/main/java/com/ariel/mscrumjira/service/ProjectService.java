package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.ProjectState;
import com.ariel.mscrumjira.dto.ProjectAuditDto;
import com.ariel.mscrumjira.dto.ProjectCreateDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;

public interface ProjectService {
	
	List<ProjectDto>findAll();
	
	List<ProjectAuditDto>findAllArchived();
	
	ProjectDto findByProjectKey(Integer projectKey);
	
	ProjectAuditDto findByProjectKeyArchived(Integer projectKey);
	
	ProjectDto findById(UUID id);		
	
	UUID create(ProjectCreateDto dto, String token);
	
	ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token);				

	boolean existsByProjectKey(Integer projectKey);
	
	
	
}
