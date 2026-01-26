package com.ariel.scrumjira.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.scrumjira.entity.Team;


public interface TeamRepository extends JpaRepository<Team, UUID> {
	
	List<Team>findByTeamKey(String teamKey);
	
	List<Team>findByUsername(String username);
	
	boolean existsByTeamKey(String teamKey);
	
	Optional<Team>findByTeamKeyAndUsername(String teamKey, String username);
	
	void deleteByTeamKey(String teamKey);
	
	void deleteByTeamKeyAndUsername(String teamKey, String username);
}
