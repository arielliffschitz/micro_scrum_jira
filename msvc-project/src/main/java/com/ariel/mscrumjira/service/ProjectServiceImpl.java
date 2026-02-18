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
	 private ProjectUpdateService updateService;	

	public ProjectServiceImpl(ProjectRepository repository, ProjectSprintService projectSprintService,
			AuditFeignClient auditClient, ProjectUpdateService updateService) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
		this.auditClient = auditClient;
		this.updateService = updateService;
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
		return ProjectMapper.mapToDto(repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found")));
	}	

	@Override
	@Transactional(readOnly = true)
	public ProjectDto findByProjectKey(Integer projectKey) {
		Project dao = tryToFindByProjectKey(projectKey); 
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
		return updateService.update(projectKey, projectUpdateDto, token);
	}		

	private Project tryToFindByProjectKey(Integer projectKey) {		
		return  repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
	}
}
