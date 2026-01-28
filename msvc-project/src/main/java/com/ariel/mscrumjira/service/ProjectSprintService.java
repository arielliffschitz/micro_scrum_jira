package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.entity.Project;

public interface ProjectSprintService {
	
	List<SprintDto>findByProjectKey(Integer projectKey);

	Optional<Project> findProjectByProjectKey(Integer projectKey);
	
	boolean existSprintByProjectKey(Integer projectKey);

}
