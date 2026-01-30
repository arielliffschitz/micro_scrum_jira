package com.ariel.mscrumjira.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.TaskMovementAudit;


public interface TaskMovementAuditRepository extends JpaRepository<TaskMovementAudit , UUID>{

	List<TaskMovementAudit>findByTaskNumber(Integer taskNumber);
}
