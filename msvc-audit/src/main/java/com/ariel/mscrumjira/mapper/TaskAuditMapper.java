package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.SprintAuditDto;
import com.ariel.mscrumjira.dto.SprintCreateAuditDto;
import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;
import com.ariel.mscrumjira.dto.TaskMovementAuditDto;
import com.ariel.mscrumjira.entity.ProjectAudit;
import com.ariel.mscrumjira.entity.SprintAudit;
import com.ariel.mscrumjira.entity.TaskAudit;
import com.ariel.mscrumjira.entity.TaskMovementAudit;

public class TaskAuditMapper {
	
	public static TaskMovementAuditDto mapToMovementDto(TaskMovementAudit dao) {		
		return new TaskMovementAuditDto(
				dao.getTaskNumber(),
				dao.getAuditTaskState(),				
				dao.getCreatedBy(),
				dao.getCreatedAt()				
				);
	}	
	
	public static TaskAuditDto mapToTaskDto(TaskAudit dao) {		
		return new TaskAuditDto(
				dao.getTaskNumber(),
				dao.getProjectKey(),
				dao.getSprintKey(),
				dao.getTitle(),
				dao.getDescription(),
				dao.getEstimate(),				
				dao.getCreatedBy(),
				dao.getCreatedAt()				
				);
	}

	public static TaskAudit mapToTaskDaoFromCreate(TaskAuditCreateDto createDto) {
		return new TaskAudit(
				createDto.taskNumber(),
				createDto.projectKey(),
				createDto.sprintKey(),
				createDto.title(),
				createDto.description(),
				createDto.estimate(),				
				createDto.createdBy(),
				createDto.createdAt()				
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

	public static ProjectAudit mapToProjectDaoFromCreate(ProjectCreateAuditDto createDto) {
		
		return new ProjectAudit(
				createDto.projectKey(),
				createDto.name(),
				createDto.description()
				);
	}	

}
