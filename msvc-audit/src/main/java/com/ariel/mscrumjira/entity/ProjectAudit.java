package com.ariel.mscrumjira.entity;

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
@Table(name = "project_audit")
public class ProjectAudit extends BaseCreateEntity {  
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;
	
	@Column(name = "project_key")
	private Integer projectKey;	  
	
	private String name;
	
	private String description;

	public ProjectAudit() {		
	}
	
	public ProjectAudit(Integer projectKey, String name, String description) {		
		this.projectKey = projectKey;
		this.name = name;
		this.description = description;
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
	
	
}


