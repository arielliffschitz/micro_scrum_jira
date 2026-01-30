package com.ariel.mscrumjira.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseCreateEntity;
import com.ariel.mscrumjira.domain.enums.AuditTaskState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_movement_audit")
public class TaskMovementAudit extends BaseCreateEntity{
	
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "task_number", nullable = false)
    private Integer taskNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "audit_task_state")
    private AuditTaskState auditTaskState;

	public TaskMovementAudit() {		
	}
	
	public TaskMovementAudit(Integer taskNumber, AuditTaskState auditTaskState) {
		super();
		this.taskNumber = taskNumber;
		this.auditTaskState = auditTaskState;
	}

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public AuditTaskState getAuditTaskState() {
		return auditTaskState;
	}

	public void setAuditTaskState(AuditTaskState auditTaskState) {
		this.auditTaskState = auditTaskState;
	}  
    
    

}
