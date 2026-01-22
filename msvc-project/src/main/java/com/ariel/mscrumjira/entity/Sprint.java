package com.ariel.mscrumjira.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseEntity;
import com.ariel.mscrumjira.domain.enums.SprintState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sprint")
public class Sprint extends BaseEntity {	

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;		

	@Column(name = "sprint_key", nullable = false)
	private Integer sprintKey;		 

	@NotNull
	@JsonIgnoreProperties({"sprints"})
	@ManyToOne(optional = false)
	@JoinColumn(name = "project_id")
	private Project project;		   	    

	@NotNull			
	private String teamKey;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "sprint_state", nullable = false)
	private SprintState state;

	@NotNull
	@Column(name = "start_date", nullable = false )
	private LocalDateTime startDate;

	@NotNull
	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	public Sprint() {			
	}


	


	public Sprint( Project project,  String teamKey,  SprintState state,
			 LocalDateTime startDate,  LocalDateTime endDate) {		
		this.project = project;
		this.teamKey = teamKey;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}		

	public String getTeamKey() {
		return teamKey;
	}


	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
	}


	public SprintState getState() {
		return state;
	}

	public void setState(SprintState state) {
		this.state = state;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Integer getSprintKey() {
		return sprintKey;
	}

	public UUID getId() {
		return id;
	}  



}
