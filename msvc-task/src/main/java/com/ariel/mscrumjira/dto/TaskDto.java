package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ariel.mscrumjira.domain.enums.TaskState;

public class TaskDto {	
	
	private Integer taskNumber;	
	private Integer projectKey;	
	private String title;	
	private String description;	
	private Integer priority;	
	private Integer estimate;	
	private TaskState taskState;	
	private Integer sprintKey;	
	private List<TaskMovementAuditDto> taskMovements;
	private LocalDateTime startDate;	
	private LocalDateTime endDate;	
	private String createdBy;	
	private LocalDateTime createdAt;	
	private String updatedBy;	
	private LocalDateTime updatedAt;	
	private Boolean sprint;
	
	public TaskDto(Integer taskNumber, Integer projectKey, String title, String description, Integer priority,
			Integer estimate, TaskState taskState, Integer sprintKey, LocalDateTime startDate, LocalDateTime endDate,
			String createdBy, LocalDateTime createdAt, String updatedBy, LocalDateTime updatedAt) {
		
		this.taskNumber = taskNumber;
		this.projectKey = projectKey;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.estimate = estimate;
		this.taskState = taskState;
		this.sprintKey = sprintKey;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.sprint = true;
	}

	public TaskDto(Integer taskNumber, Integer projectKey, String title, String description, Integer priority,
			Integer estimate, String createdBy, LocalDateTime createdAt, String updatedBy, LocalDateTime updateAt) {
		
		this.taskNumber = taskNumber;
		this.projectKey = projectKey;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.estimate = estimate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updateAt;
		this.sprint = false;
	}

	
	public TaskDto(Integer taskNumber, Integer projectKey, String title, String description, Integer estimate,
			TaskState taskState, String createdBy, LocalDateTime createdAt) {		
		this.taskNumber = taskNumber;
		this.projectKey = projectKey;
		this.title = title;
		this.description = description;
		this.estimate = estimate;
		this.taskState = taskState;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public Integer getProjectKey() {
		return projectKey;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getPriority() {
		return priority;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public TaskState getTaskState() {
		return taskState;
	}

	public Integer getSprintKey() {
		return sprintKey;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Boolean getSprint() {
		return sprint;
	}

	public List<TaskMovementAuditDto> getTaskMovements() {
		return taskMovements;
	}

	public void setTaskMovements(List<TaskMovementAuditDto> taskMovements) {
		this.taskMovements = taskMovements;
	}			

}
