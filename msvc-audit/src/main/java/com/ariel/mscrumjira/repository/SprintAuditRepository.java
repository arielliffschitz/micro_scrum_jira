package com.ariel.mscrumjira.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.SprintAudit;

public interface SprintAuditRepository  extends JpaRepository<SprintAudit, UUID>{

	List<SprintAudit>findByProjectKey(Integer projectKey);
}
