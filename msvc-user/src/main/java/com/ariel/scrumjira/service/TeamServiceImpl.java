package com.ariel.scrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.service.PersistenceMetadataUtil;
import com.ariel.scrumjira.client.SprintFeignClient;
import com.ariel.scrumjira.dto.TeamCreateDto;
import com.ariel.scrumjira.dto.TeamDto;
import com.ariel.scrumjira.entity.Team;
import com.ariel.scrumjira.mapper.TeamMapper;
import com.ariel.scrumjira.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

	final private TeamRepository repository;
	
	final private UserService  userService;

	private final SprintFeignClient sprintClient;	

	public TeamServiceImpl(TeamRepository repository, UserService userService, SprintFeignClient sprintClient) {		
		this.repository = repository;
		this.userService = userService;
		this.sprintClient = sprintClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeamDto> findAll() {
		return repository.findAll().stream()				                
				.map(TeamMapper::mapToDto)
				.collect(Collectors.toList());      
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeamDto> findByTeamKey(String teamKey) {
		return repository.findByTeamKey(teamKey).stream()				               
				.map(TeamMapper::mapToDto)
				.collect(Collectors.toList());		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeamDto> findByUsername(String username) {
		return repository.findByUsername(username).stream()				              
				.map(TeamMapper::mapToDto)
				.collect(Collectors.toList());		
	}

	@Override
	@Transactional(readOnly = true)
	public TeamDto findById(UUID id) {
		return TeamMapper.mapToDto(repository.findById(id).orElseThrow());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TeamDto> findByTeamKeyAndUsername(String teamKey, String username) {

		return repository.findByTeamKeyAndUsername(teamKey, username).map(TeamMapper::mapToDto);
	}

	@Override
	public boolean existsByTeamKey(String teamKey) {		
		return repository.existsByTeamKey(teamKey);
	}

	@Override
	@Transactional
	public UUID create(TeamCreateDto createDto, String token) {		 
		validateCreate(createDto);		
		Team dao =  new Team(createDto.teamKey(),createDto.username());						
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);

		return  repository.save(dao).getId();
	}

	private void validateCreate(TeamCreateDto createDto) {
		userService.findByUsername(createDto.username()).orElseThrow( ()-> new IllegalArgumentException("the  username doesn't exist")); 					
		
		repository.findByTeamKeyAndUsername(createDto.teamKey(), createDto.username()).ifPresent(t ->{
		 throw new IllegalArgumentException("the teamKey plus username just exist"); 
		});		
	}

	@Override
	@Transactional
	public void deleteByTeamKey(String teamKey) {
		if(sprintClient.existsByTeamKey(teamKey)) {
			throw new IllegalArgumentException("Forbiden delete, exist a sprint asign to this teamKey: "+ teamKey);}
		else repository.deactivateTeam(teamKey);		
	}

	@Override
	@Transactional
	public void deleteByTeamKeyAndUsername(TeamCreateDto dto) {
		if (findByTeamKey(dto.teamKey()).size() == 1) 
			deleteByTeamKey(dto.teamKey());
		else repository.deactivateUsernameInTeam(dto.teamKey(), dto.username());		
	}

}
