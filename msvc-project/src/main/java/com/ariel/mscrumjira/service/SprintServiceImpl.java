package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.client.AuditFeignClient;
import com.ariel.mscrumjira.client.UserFeignClient;
import com.ariel.mscrumjira.domain.enums.SprintState;
import com.ariel.mscrumjira.dto.SprintCreateDto;
import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.entity.Sprint;
import com.ariel.mscrumjira.mapper.SprintMapper;
import com.ariel.mscrumjira.repository.SprintRepository;

@Service
public class SprintServiceImpl implements SprintService {
	
	 private SprintRepository repository;
	
	 private ProjectSprintService projectSprintService;
	
	 private UserFeignClient userClient;
	 private AuditFeignClient auditClient;

	public SprintServiceImpl(SprintRepository repository, ProjectSprintService projectSprintService,
			UserFeignClient userClient,AuditFeignClient auditClient) {		
		this.repository = repository;
		this.projectSprintService = projectSprintService;
		this.userClient = userClient;
		this.auditClient =auditClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SprintDto> findAll() {
		return repository.findAll().stream()				                
						 .map(SprintMapper::mapToDto)
						 .collect(Collectors.toList());        
	}
	
	@Override
	@Transactional(readOnly = true)
	public SprintDto findById(UUID id) {
		return SprintMapper.mapToDto(repository.findById(id).orElseThrow());
				
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SprintDto> findBySprintKey(Integer sprintKey) {
		return repository.findBySprintKey(sprintKey).map(SprintMapper::mapToDto);
	}

	@Override
	@Transactional
	public UUID create(SprintCreateDto createDto, String token) {
		validateCreate(createDto.teamKey());
		Sprint dao = createDao(createDto);		
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		
		return repository.save(dao).getId();
	}	
		
	@Override
	public boolean existsBySprintKey(Integer sprintKey) {		
		return repository.existsBySprintKey(sprintKey);
	}

	@Override
	public boolean existsByTeamKey(String teamKey) {
		return repository.existsByTeamKey(teamKey);
	}	
	
	@Override
	@Transactional
	public SprintDto updateState(Integer sprintKey, SprintState state, String token) {
		Sprint dao = findSprint(sprintKey);
		dao.setState(state);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);

		if(state.equals(SprintState.ARCHIVED))
			archiveSprint(dao,token );					
		else 				
			repository.save(dao);

		return SprintMapper.mapToDto(dao);
	}

	private void archiveSprint(Sprint dao, String token) {
		auditClient.createSprint(SprintMapper.mapToSprintCreateAuditDto(dao), token);			
		repository.delete(dao);
		
	}

	private Sprint findSprint(Integer sprintKey) {
		return repository.findBySprintKey(sprintKey)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprint: "+ sprintKey+" not found"));
	}
	
	private void validateCreate(String teamKey) {
		 if ( ! userClient.existsByTeamKey(teamKey)) 
			 throw new IllegalArgumentException("The team: "+teamKey+ " doesn't exist");		
	}
	
	private Sprint createDao(SprintCreateDto createDto) {
		return new Sprint(projectSprintService.findProjectByProjectKey(
							createDto.projectKey()).orElseThrow(),
					        createDto.teamKey(),
					        SprintState.PLANNED,
					        createDto.startDate(),
					        createDto.endDate()				        
				);		
	}	
}
