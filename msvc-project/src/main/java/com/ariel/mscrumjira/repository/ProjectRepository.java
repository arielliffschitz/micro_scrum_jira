package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;


import com.ariel.mscrumjira.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, UUID> {
	
	Optional<Project>findByProjectKey(Integer projectKey);
	
	void deleteByProjectKey(Integer projectKey);

}
