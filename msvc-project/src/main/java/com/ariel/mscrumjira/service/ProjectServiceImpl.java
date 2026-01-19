package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	
	

	public ProjectServiceImpl(ProjectRepository repository) {		
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectDto> findAll() {
		return StreamSupport.stream(repository.findAll()
				.spliterator(), false)                
				.map(ProjectMapper::mapToDto)
				.collect(Collectors.toList());        
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProjectDto> findByProjectKey(Integer projectKey) {
		return repository.findByProjectKey(projectKey)
                .map(ProjectMapper::mapToDto);
                
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
	public void deleteByProjectKey(Integer projectKey) {
		repository.deleteByProjectKey(projectKey);
	}	

	@Override
	public ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token) {
		
		Project dao = repository.findByProjectKey(projectKey)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Project not found for projectKey: " + projectKey));

		ProjectMapper.applyUpdateToProject(dao, projectUpdateDto);
		    AuditUtil.BaseEntityUpdateFields(dao, token);
		    
		    return ProjectMapper.mapToDto(repository.save(dao));
	}

	@Override
	public ProjectDto findById(UUID id) {
		
		return ProjectMapper.mapToDto(repository.findById(id).orElseThrow());
	}

}
