package com.ariel.mscrumjira.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseCreateEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@AttributeOverrides({
	@AttributeOverride(name="createdBy", column=@Column(name="archived_by")),
	@AttributeOverride(name="createdAt", column=@Column(name="archived_at"))
	})
@Entity
@Table(name = "sprint_audit")
public class SprintAudit extends BaseCreateEntity {    
	
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;		

	@Column(name = "sprint_key")
	private Integer sprintKey;	 
	
	@Column(name = "project_key")
	private Integer projectKey;	   	    
				
	private String teamKey;
	
	@Column(name = "start_date" )
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;		

	public SprintAudit() {		
	}		

	public SprintAudit(Integer sprintKey, Integer projectKey, String teamKey, LocalDateTime startDate,
			LocalDateTime endDate) {
		super();
		this.sprintKey = sprintKey;
		this.projectKey = projectKey;
		this.teamKey = teamKey;
		this.startDate = startDate;
		this.endDate = endDate;		
	}

	public Integer getSprintKey() {
		return sprintKey;
	}

	public void setSprintKey(Integer sprintKey) {
		this.sprintKey = sprintKey;
	}

	public Integer getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(Integer projectKey) {
		this.projectKey = projectKey;
	}

	public String getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
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
	
}
