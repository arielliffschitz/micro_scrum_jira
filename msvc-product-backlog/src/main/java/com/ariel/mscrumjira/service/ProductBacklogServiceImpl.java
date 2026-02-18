package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.ProjectFeignClient;
import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;
import com.ariel.mscrumjira.mapper.ProductBacklogItemMapper;
import com.ariel.mscrumjira.repository.ProductBacklogRepository;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {

	private ProductBacklogRepository repository;
	private ProjectFeignClient projectClient;

	

	public ProductBacklogServiceImpl(ProductBacklogRepository repository, ProjectFeignClient projectClient) {	
		this.repository = repository;
		this.projectClient = projectClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductBacklogItemDto> findAll() {
		return StreamSupport.stream(repository.findAll()
				.spliterator(), false)                
				.map(ProductBacklogItemMapper::mapToDto)
				.collect(Collectors.toList());        
	}   

	@Override
	@Transactional
	public ProductBacklogItemDto save(ProductBacklogItemDto backlogItemDto,  String token) {  			
		ProductBacklogItem dao = ProductBacklogItemMapper.mapToDao(backlogItemDto);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);

		return ProductBacklogItemMapper.mapToDto(repository.save(dao));
	}

	@Override   
	@Transactional
	public UUID create(ProductCreateDto dto, String token) {  
		validateProjectKey(dto.projectKey());
		ProductBacklogItem dao =  ProductBacklogItemMapper.mapToDaoCreate(dto);
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);

		return  repository.save(dao).getId();
	}               	

	@Override
	@Transactional
	public void deleteByTaskNumber(Integer taskNumber) {
		repository.deleteByTaskNumber(taskNumber);
	}   

	@Override
	@Transactional(readOnly = true)
	public ProductBacklogItemDto findByTaskNumber(Integer taskNumber) {		
		return	ProductBacklogItemMapper.mapToDto(tryfindByTaskNumber(taskNumber));		 
	}  

	@Override
	@Transactional(readOnly = true)
	public ProductBacklogItemDto findById(UUID id) {
		return ProductBacklogItemMapper.mapToDto(repository.findById(id).orElseThrow());
	} 

	@Override
	@Transactional
	public ProductBacklogItemDto update(Integer taskNumber, UpdateSprintBacklogDto taskUpdate, String token) {

		ProductBacklogItem dao = tryfindByTaskNumber(taskNumber);
		ProductBacklogItemMapper.applyUpdateToProduct(dao, taskUpdate);
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);
		
		return ProductBacklogItemMapper.mapToDto(repository.save(dao));
	}
	
	private ProductBacklogItem tryfindByTaskNumber(Integer taskNumber) {
		return repository.findByTaskNumber(taskNumber)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Product with taskNumber: " +taskNumber+" not found"));
	}

	private void validateProjectKey( Integer projectKey) {
		if(!projectClient.existsByTeamKey(projectKey))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This projectKey: "+projectKey + " doesn't exist");
	}


}
