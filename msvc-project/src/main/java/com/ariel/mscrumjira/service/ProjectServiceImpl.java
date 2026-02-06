package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.client.AuditFeignClient;
import com.ariel.mscrumjira.domain.enums.ProjectState;
import com.ariel.mscrumjira.dto.ProjectAuditDto;
import com.ariel.mscrumjira.dto.ProjectCreateDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;
import com.ariel.mscrumjira.entity.Project;
import com.ariel.mscrumjira.mapper.ProjectMapper;
import com.ariel.mscrumjira.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	 private ProjectRepository repository;

	 private ProjectSprintService projectSprintService;

	 private AuditFeignClient auditClient;	

	public ProjectServiceImpl(ProjectRepository repository, ProjectSprintService projectSprintService, 
								AuditFeignClient auditClient) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
		this.auditClient = auditClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectDto> findAll() {
		return repository.findAll().stream()				                
				.map(dao->{
					ProjectDto dto = ProjectMapper.mapToDto(dao);
					dto.setSprints(projectSprintService.findByProjectKey(dto.getProjectKey()));
					return dto;
				})
				.collect(Collectors.toList());				
	}	

	@Override
	@Transactional(readOnly = true)
	public List<ProjectAuditDto> findAllArchived() {		
		return auditClient.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public ProjectDto findById(UUID id) {		
		return ProjectMapper.mapToDto(repository.findById(id).orElseThrow());
	}	

	@Override
	@Transactional(readOnly = true)
	public ProjectDto findByProjectKey(Integer projectKey) {
		Project dao = repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
		ProjectDto dto = ProjectMapper.mapToDto(dao);
		dto.setSprints(projectSprintService.findByProjectKey(projectKey));	

		return dto;			  				
	}

	@Transactional(readOnly = true)
	public ProjectAuditDto findByProjectKeyArchived(Integer projectKey) {		
		return auditClient.findByProjectKey(projectKey);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByProjectKey(Integer projectKey) {		
		return repository.existsByProjectKey(projectKey);
	}

	@Override
	@Transactional
	public UUID create(ProjectCreateDto dto,  String token) {
		Project dao =  new Project(dto.name(),dto.description() );
		dao.setState(ProjectState.CREATED);
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		return  repository.save(dao).getId();
	}	

	@Override
	@Transactional
	public ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token) {
		Project dao = findProject(projectKey);
		ProjectMapper.applyUpdateToProject(dao, projectUpdateDto);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);

		return ProjectMapper.mapToDto(repository.save(dao));
	}	

	@Override
	@Transactional
	public ProjectDto updateState(Integer projectKey, ProjectState state, String token) {
		Project dao = findProject(projectKey);		
		dao.setState(state);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);

		if(state.equals(ProjectState.ARCHIVED)) 
			archiveProject(dao, token);		
		else 				
			repository.save(dao);

		return ProjectMapper.mapToDto(dao);
	}

	private Project findProject(Integer projectKey) {
		return repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project: "+ projectKey+" not found"));
	}

	private void archiveProject(Project dao, String token) {
		existSprintByProjectKey(dao.getProjectKey());		
		auditClient.createProject(ProjectMapper.mapToProjectCreateAuditDto(dao), token);			
		repository.delete(dao);	
	}

	private void existSprintByProjectKey(Integer projectKey) {
		if(projectSprintService.existSprintByProjectKey(projectKey)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Project: "+projectKey+
					"  has active sprints and cannot be archived"	 );	
		}		
	}
	

}
