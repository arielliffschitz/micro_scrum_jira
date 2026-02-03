package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.ProjectAudit;

public interface ProjectAuditRepository extends JpaRepository<ProjectAudit, UUID> {
	
	Optional<ProjectAudit> findByProjectKey(Integer projectKey);

}
