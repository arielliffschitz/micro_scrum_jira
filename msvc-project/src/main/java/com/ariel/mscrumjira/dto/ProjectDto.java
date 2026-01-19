package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.enums.ProjectState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectDto {
	
    private Integer projectKey;
	
    @NotBlank
    @Size(max=50)
	private String name;

    @Size(max=250)
	private String description;
    
	private ProjectState projectState;
    
    private String createdBy;   

    private LocalDateTime createdAt;   
    
    private String updatedBy;   

    private LocalDateTime updatedAt;

	public ProjectDto() {		
	}

	
	


	public ProjectDto(Integer projectKey,  String name, String description,
			ProjectState projectState, String createdBy, LocalDateTime createdAt, String updatedBy,
			LocalDateTime updatedAt) {		
		this.projectKey = projectKey;
		this.name = name;
		this.description = description;
		this.projectState = projectState;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}





	public Integer getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(Integer projectKey) {
		this.projectKey = projectKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	public ProjectState getProjectState() {
		return projectState;
	}

	public void setProjectState(ProjectState projectState) {
		this.projectState = projectState;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}   

    
}
