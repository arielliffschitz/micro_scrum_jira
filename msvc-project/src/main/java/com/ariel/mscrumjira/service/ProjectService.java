package com.ariel.mscrumjira.service;

import java.util.*;

import com.ariel.mscrumjira.dto.*;

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
