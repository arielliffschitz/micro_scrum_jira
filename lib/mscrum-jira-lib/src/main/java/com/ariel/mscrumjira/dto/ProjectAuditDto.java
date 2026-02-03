package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectAuditDto {
		
	private Integer projectKey;  

	private String name;

	private String description;

	private String archivedBy;				 

	private LocalDateTime archivedAt;

	private List<SprintAuditDto> sprints;

	public ProjectAuditDto() {			
	}

	public ProjectAuditDto(Integer projectKey, String name, String description, String archivedBy,
			LocalDateTime archivedAt, List<SprintAuditDto> sprints) {			
		this.projectKey = projectKey;
		this.name = name;
		this.description = description;
		this.archivedBy = archivedBy;
		this.archivedAt = archivedAt;
		this.sprints = sprints;
	}

	public void setSprints(List<SprintAuditDto> sprints) {
		this.sprints = sprints;
	}

	public Integer getProjectKey() {
		return projectKey;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getArchivedBy() {
		return archivedBy;
	}

	public LocalDateTime getArchivedAt() {
		return archivedAt;
	}

	public List<SprintAuditDto> getSprints() {
		return sprints;
	}





}
