package com.ariel.mscrumjira.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;
import com.ariel.mscrumjira.mapper.*;
import com.ariel.mscrumjira.repository.*;

@Service
public class ProjectUpdateService {

	private ProjectRepository repository;

	private ProjectSprintService projectSprintService;

	private AuditFeignClient auditClient;

	public ProjectUpdateService(ProjectRepository repository, ProjectSprintService projectSprintService,
			AuditFeignClient auditClient) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
		this.auditClient = auditClient;
	}

	@Transactional
	public ProjectDto update(Integer projectKey, ProjectUpdateDto projectUpdateDto, String token) {
		Project dao = findProject(projectKey);
		ProjectMapper.applyUpdateToProject(dao, projectUpdateDto);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);
		ProjectDto dto = ProjectMapper.mapToDto(dao);

		if (projectUpdateDto.state() != null && projectUpdateDto.state().equals(ProjectState.ARCHIVED)) {
			archiveProject(dao, token);
		} else {
			repository.save(dao);
		}
		return dto;
	}

	private Project findProject(Integer projectKey) {
		return repository.findByProjectKey(projectKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project: "+ projectKey+" not found"));
	}

	@Transactional
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
