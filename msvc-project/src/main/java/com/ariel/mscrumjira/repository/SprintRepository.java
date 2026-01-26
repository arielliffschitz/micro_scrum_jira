package com.ariel.mscrumjira.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, UUID>{
	
	Optional<Sprint>findBySprintKey(Integer sprintKey);
	
	List<Sprint> findByProject_ProjectKey(Integer projectKey);
	
	void deleteBySprintKey(Integer sprintKey);
	
	boolean existsBySprintKey(Integer sprintKey);
	
	boolean existsByTeamKey(String teamKey);
}
