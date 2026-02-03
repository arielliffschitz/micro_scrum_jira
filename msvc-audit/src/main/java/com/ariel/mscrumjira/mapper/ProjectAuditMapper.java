package com.ariel.mscrumjira.mapper;

import java.util.ArrayList;

import com.ariel.mscrumjira.dto.ProjectAuditDto;
import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;
import com.ariel.mscrumjira.entity.ProjectAudit;
import com.ariel.mscrumjira.entity.SprintAudit;

public class ProjectAuditMapper {	

	public static ProjectAudit mapToProjectDaoFromCreate(ProjectCreateAuditDto createDto) {

		return new ProjectAudit(
				createDto.projectKey(),
				createDto.name(),
				createDto.description()
				);
	}	

	public static ProjectAuditDto mapToProjectDtoFromDao( ProjectAudit dao) {

		return new ProjectAuditDto(
				dao.getProjectKey(),
				dao.getName(),
				dao.getDescription(),
				dao.getCreatedBy(),
				dao.getCreatedAt(),
				new ArrayList<SprintAuditDto>()
				);
	}	

	public static SprintAudit mapToSprintDaoFromCreate(SprintCreateAuditDto createDto) {

		return new SprintAudit (
				createDto.sprintKey(),
				createDto.projectKey(),
				createDto.teamKey(),
				createDto.startDate(),
				createDto.endDate()				
				);					
	}

	public static  SprintAuditDto mapToSprintDtoFromDao(SprintAudit dao) {
		return new SprintAuditDto(
				dao.getSprintKey(),
				dao.getProjectKey(),
				dao.getTeamKey(),
				dao.getStartDate(),
				dao.getEndDate(),
				dao.getCreatedBy(),
				dao.getCreatedAt()			
				);
	}

}
