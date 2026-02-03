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

	final private ProjectRepository repository;

	final private ProjectSprintService projectSprintService;

	final private AuditFeignClient auditClient;	

	public ProjectServiceImpl(ProjectRepository repository, ProjectSprintService projectSprintService,
			AuditFeignClient auditClient) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
		this.auditClient = auditClient;
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
		AuditUtil.BaseEntityCreatedFields(dao, token);
		return  repository.save(dao).getId();
	}	

	@Override
	@Transactional
	public ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token) {

		Project dao = repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project: "+ projectKey+" not found"));

		ProjectMapper.applyUpdateToProject(dao, projectUpdateDto);
		AuditUtil.BaseEntityUpdateFields(dao, token);

		return ProjectMapper.mapToDto(repository.save(dao));
	}

	@Override
	@Transactional
	public ProjectDto updateState(Integer projectKey, ProjectState state, String token) {
		Project dao = repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project: "+ projectKey+" not found"));			
		dao.setState(state);
		AuditUtil.BaseEntityUpdateFields(dao, token);

		if(state.equals(ProjectState.ARCHIVED)) 
			archiveProject(dao, token);		
		else 				
			repository.save(dao);

		return ProjectMapper.mapToDto(dao);
	}

	private void archiveProject(Project dao, String token) {
		if(projectSprintService.existSprintByProjectKey(dao.getProjectKey())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Project: "+ dao.getProjectKey()+
					"  has active sprints and cannot be archived"	 );	
		}else {
			auditClient.createProject(ProjectMapper.mapToProjectCreateAuditDto(dao), token);			
			repository.delete(dao);	
		}
	}

}
