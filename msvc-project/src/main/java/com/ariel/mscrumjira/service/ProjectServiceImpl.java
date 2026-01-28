package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.domain.enums.ProjectState;
import com.ariel.mscrumjira.dto.ProjectCreateDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;
import com.ariel.mscrumjira.entity.Project;
import com.ariel.mscrumjira.mapper.ProjectMapper;
import com.ariel.mscrumjira.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

	final private ProjectRepository repository;
	
	final private ProjectSprintService projectSprintService;
	

	public ProjectServiceImpl(ProjectRepository repository, ProjectSprintService projectSprintService) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectDto> findAll() {
		
		List<ProjectDto> projectList = repository.findAll().stream()				                
				.map(ProjectMapper::mapToDto)
				.collect(Collectors.toList());		
		
		for (ProjectDto p : projectList) {
			p.setSprints(projectSprintService.findByProjectKey(p.getProjectKey()));
		} 
		
		return projectList;        
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectDto findById(UUID id) {		
		return ProjectMapper.mapToDto(repository.findById(id).orElseThrow());
	}	

	@Override
	@Transactional(readOnly = true)
	public Optional<ProjectDto> findByProjectKey(Integer projectKey) {
		return repository.findByProjectKey(projectKey)
				.map(dao->{					
					ProjectDto dto = ProjectMapper.mapToDto(dao);
					dto.setSprints(projectSprintService.findByProjectKey(projectKey));
					return dto;
				});
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByProjectKey(Integer projectKey) {		
		 return repository.existsByProjectKey(projectKey);
	}
	
	@Override
	@Transactional
	public UUID create(ProjectCreateDto createDto,  String token) {
		Project dao =  ProjectMapper.mapToCreateDao(createDto);
		dao.setState(ProjectState.CREATED);
		AuditUtil.BaseEntityCreatedFields(dao, token);
		return  repository.save(dao).getId();
	}	

	@Override
	@Transactional
	public void deleteByProjectKey(Integer projectKey) {
		if(projectSprintService.existSprintByProjectKey(projectKey)) {
			throw new IllegalArgumentException("Forbiden delete, exist a sprint asign to this  projectKey: "+ projectKey);
		}
		else repository.deleteByProjectKey(projectKey);
	}	

	@Override
	@Transactional
	public ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token) {

		Project dao = repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new EntityNotFoundException(
						"Project not found for projectKey: " + projectKey));

		ProjectMapper.applyUpdateToProject(dao, projectUpdateDto);
		AuditUtil.BaseEntityUpdateFields(dao, token);

		return ProjectMapper.mapToDto(repository.save(dao));
	}

	



}
