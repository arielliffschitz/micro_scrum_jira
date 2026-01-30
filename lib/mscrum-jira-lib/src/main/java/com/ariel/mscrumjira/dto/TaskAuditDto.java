package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TaskAuditDto  {

	private Integer taskNumber;
	private Integer projectKey;
	private Integer sprintKey;
	private String title;
	private String description;	
	private Integer estimate;	               
	private List<TaskMovementAuditDto> taskMovements;
	private String createdBy;
	private LocalDateTime createdAt;
	
	public TaskAuditDto() {		
	}

	
	public TaskAuditDto(Integer taskNumber, Integer projectKey, Integer sprintKey, String title, String description,
			Integer estimate, String createdBy, LocalDateTime createdAt) {		
		this.taskNumber = taskNumber;
		this.projectKey = projectKey;
		this.sprintKey = sprintKey;
		this.title = title;
		this.description = description;
		this.estimate = estimate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}


	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(Integer projectKey) {
		this.projectKey = projectKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public Integer getSprintKey() {
		return sprintKey;
	}

	public void setSprintKey(Integer sprintKey) {
		this.sprintKey = sprintKey;
	}

	public List<TaskMovementAuditDto> getTaskMovements() {
		return taskMovements;
	}

	public void setTaskMovements(List<TaskMovementAuditDto> taskMovements) {
		this.taskMovements = taskMovements;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
