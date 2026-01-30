package com.ariel.mscrumjira.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_audit")
public class TaskAudit {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")
    private UUID id;
	
	@Column(name = "task_number")
	private Integer taskNumber;
	
	@Column(name = "project-key")
	private Integer projectKey;
	
	@Column(name = "sprint-key")
	private Integer sprintKey;	
	
	private String title;
	private String description;	
	private Integer estimate;
	
	@Column(name ="created-by")
	private String createdBy;
	
	@Column(name ="created-at")
	private LocalDateTime createdAt;
	
	public TaskAudit() {		
	}

	public TaskAudit(Integer taskNumber, Integer projectKey, Integer sprintKey, String title, String description,
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

	public Integer getSprintKey() {
		return sprintKey;
	}

	public void setSprintKey(Integer sprintKey) {
		this.sprintKey = sprintKey;
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
