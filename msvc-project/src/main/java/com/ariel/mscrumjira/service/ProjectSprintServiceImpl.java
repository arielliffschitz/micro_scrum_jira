package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.entity.Project;
import com.ariel.mscrumjira.mapper.ProjectMapper;
import com.ariel.mscrumjira.mapper.SprintMapper;
import com.ariel.mscrumjira.repository.ProjectRepository;
import com.ariel.mscrumjira.repository.SprintRepository;

@Service
public class ProjectSprintServiceImpl implements ProjectSprintService {
	
	final private SprintRepository sprintRepository;
	final private ProjectRepository projectRepository;
	
	
	
	public ProjectSprintServiceImpl(SprintRepository sprintRepository, ProjectRepository projectRepository) {		
		this.sprintRepository = sprintRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public List<SprintDto> findByProjectKey(Integer projectKey) {
		return sprintRepository.findByProject_ProjectKey(projectKey).stream()				                
				 .map(SprintMapper::mapToDto)
				 .collect(Collectors.toList()); 
	}

	@Override
	public Optional<Project> findProjectByProjectKey(Integer projectKey) {
		return projectRepository.findByProjectKey(projectKey);				
	}

	@Override
	public boolean existSprintByProjectKey(Integer projectKey) {
		
		return sprintRepository.existsByProject_ProjectKey(projectKey);
	}

}
