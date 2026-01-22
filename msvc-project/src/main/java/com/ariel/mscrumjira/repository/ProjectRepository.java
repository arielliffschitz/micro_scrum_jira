package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
	
	Optional<Project>findByProjectKey(Integer projectKey);
	
	void deleteByProjectKey(Integer projectKey);

}
