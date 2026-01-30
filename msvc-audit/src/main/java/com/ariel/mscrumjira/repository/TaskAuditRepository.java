package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.TaskAudit;

public interface TaskAuditRepository extends JpaRepository<TaskAudit, UUID> {
	Optional<TaskAudit>findByTaskNumber(Integer taskNumber);

}
