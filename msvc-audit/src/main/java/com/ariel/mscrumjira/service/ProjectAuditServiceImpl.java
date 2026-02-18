package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.ProjectAudit;
import com.ariel.mscrumjira.entity.SprintAudit;
import com.ariel.mscrumjira.mapper.ProjectAuditMapper;
import com.ariel.mscrumjira.repository.ProjectAuditRepository;
import com.ariel.mscrumjira.repository.SprintAuditRepository;

@Service
public class ProjectAuditServiceImpl implements ProjectAuditService {

	 private ProjectAuditRepository projectRepository;	
	 private SprintAuditRepository  sprintRepository;		

	public ProjectAuditServiceImpl(ProjectAuditRepository projectRepository, SprintAuditRepository sprintRepository) {		
		this.projectRepository = projectRepository;
		this.sprintRepository = sprintRepository;
	}
	
	@Override
	@Transactional
	public void createProject(ProjectCreateAuditDto createDto, String token) {
		ProjectAudit  dao = ProjectAuditMapper.mapToProjectDaoFromCreate(createDto);		
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		projectRepository.save(dao);	
		
	}
	@Override
	@Transactional
	public void createSprint(SprintCreateAuditDto createDto, String token) {
		SprintAudit  dao = ProjectAuditMapper.mapToSprintDaoFromCreate(createDto);		
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		sprintRepository.save(dao);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectAuditDto> findAll() {
		
		return projectRepository.findAll().stream()
				.map(ProjectAuditMapper::mapToProjectDtoFromDao)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectAuditDto findByProjectKey(Integer projectKey) {
		ProjectAudit dao = tryTofindByProjectKey(projectKey);
		ProjectAuditDto dto = ProjectAuditMapper.mapToProjectDtoFromDao(dao);			
		dto.setSprints(getSprintsByProjectKey(projectKey));
		
		return dto;
	}

	private List<SprintAuditDto> getSprintsByProjectKey(Integer projectKey) {		
		return sprintRepository.findByProjectKey(projectKey)
				.stream()
				.map(ProjectAuditMapper::mapToSprintDtoFromDao)
				.toList();
	}

	private ProjectAudit tryTofindByProjectKey(Integer projectKey) {		
		return  projectRepository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectKey: " +projectKey+ " not found"));
	}
}
