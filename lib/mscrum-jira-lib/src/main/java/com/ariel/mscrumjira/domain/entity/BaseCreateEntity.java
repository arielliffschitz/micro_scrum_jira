package com.ariel.mscrumjira.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseCreateEntity {

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	
	public BaseCreateEntity() {		
	}
	
	public BaseCreateEntity(String createdBy, LocalDateTime createdAt) {		
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}   

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}   

}
