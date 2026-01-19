package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.mscrumjira.dto.ProjectCreateDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;

public interface ProjectService {
	
	List<ProjectDto>findAll();
	
	ProjectDto findById(UUID id);
	
	Optional<ProjectDto> findByProjectKey(Integer projectKey);
	
	UUID create(ProjectCreateDto dto, String token);
	
	ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token);
	
	void deleteByProjectKey(Integer projectKey);
	
	
	
}
