package com.ariel.scrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.scrumjira.dto.TeamCreateDto;
import com.ariel.scrumjira.dto.TeamDto;

public interface TeamService {
	
	List<TeamDto>findAll();
	
	List<TeamDto> findByTeamKey(String teamKey);
	
	List<TeamDto> findByUsername(String username);
	
	TeamDto findById(UUID id);
	
	Optional<TeamDto>findByTeamKeyAndUsername (String teamKey, String username);		
	
	UUID create(TeamCreateDto dto, String token);
	
	boolean existsByTeamKey(String teamKey);		
	
	void deleteByTeamKey(String teamKey);

}
