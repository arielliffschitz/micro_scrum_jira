package com.ariel.mscrumjira.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseEntity;
import com.ariel.mscrumjira.domain.enums.ProjectState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "project")
public class Project extends BaseEntity {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;
	 
	@Column(name = "project_key", nullable = false, unique = true)
    private Integer projectKey;

	@NotNull
	@Column(nullable = false)
	private String name;

	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private ProjectState state;
	
	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	private List<Sprint> sprints;

	public Project() {		
	}
	
	public Project(String name, String description) {		
		this.name = name;
		this.description = description;
	}

	public Integer getProjectKey() {
		return projectKey;
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

	public ProjectState getState() {
		return state;
	}

	public void setState(ProjectState state) {
		this.state = state;
	}

	public UUID getId() {
		return id;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}	

	
}
