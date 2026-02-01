package com.ariel.mscrumjira.service;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;
import com.ariel.mscrumjira.entity.ProjectAudit;
import com.ariel.mscrumjira.entity.SprintAudit;
import com.ariel.mscrumjira.mapper.TaskAuditMapper;
import com.ariel.mscrumjira.repository.ProjectAuditRepository;
import com.ariel.mscrumjira.repository.SprintAuditRepository;

@Service
public class ProjectAuditServiceImpl implements ProjectAuditService {

	final private   ProjectAuditRepository projectRepository;	
	final private SprintAuditRepository sprintRepository;		

	public ProjectAuditServiceImpl(ProjectAuditRepository projectRepository, SprintAuditRepository sprintRepository) {		
		this.projectRepository = projectRepository;
		this.sprintRepository = sprintRepository;
	}
	
	@Override
	public void createProject(ProjectCreateAuditDto createDto, String token) {
		ProjectAudit  dao = TaskAuditMapper.mapToProjectDaoFromCreate(createDto);		
		AuditUtil.BaseEntityCreatedFields(dao, token);
		projectRepository.save(dao);	
		
	}
	@Override
	public void createSprint(SprintCreateAuditDto createDto, String token) {
		SprintAudit  dao = TaskAuditMapper.mapToSprintDaoFromCreate(createDto);		
		AuditUtil.BaseEntityCreatedFields(dao, token);
		sprintRepository.save(dao);		
	}
}
