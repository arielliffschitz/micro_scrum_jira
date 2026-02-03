package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	final private SprintRepository repository;
	
	final private ProjectSprintService projectSprintService;
	
	final private UserFeignClient userClient;
	final private AuditFeignClient auditClient;

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
		AuditUtil.BaseEntityCreatedFields(dao, token);
		
		return repository.save(dao).getId();
	}

	@Override
	@Transactional
	public SprintDto updateState(Integer sprintKey, SprintState state, String token) {
		Sprint dao = repository.findBySprintKey(sprintKey).orElseThrow();
		dao.setState(state);
		AuditUtil.BaseEntityUpdateFields(dao, token);
		
		if(state.equals(SprintState.ARCHIVED)) {
			auditClient.createSprint(SprintMapper.mapToSprintCreateAuditDto(dao), token);			
			repository.delete(dao);
		}
		else 				
			repository.save(dao);

		return SprintMapper.mapToDto(dao);
	}

	@Override
	@Transactional
	public void deleteBySprintKey(Integer sprintKey) {
		repository.deleteBySprintKey(sprintKey);		
	}	
	
	@Override
	public boolean existsBySprintKey(Integer sprintKey) {		
		return repository.existsBySprintKey(sprintKey);
	}

	@Override
	public boolean existsByTeamKey(String teamKey) {
		return repository.existsByTeamKey(teamKey);
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
