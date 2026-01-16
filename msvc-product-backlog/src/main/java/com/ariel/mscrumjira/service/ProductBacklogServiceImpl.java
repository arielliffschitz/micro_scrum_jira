package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.mapper.ProductBacklogItemMapper;
import com.ariel.mscrumjira.repository.ProductBacklogRepository;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService {

	final private ProductBacklogRepository repository;

	public ProductBacklogServiceImpl(ProductBacklogRepository repository) {
		this.repository = repository;
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
	public ProductBacklogItemDto save(ProductBacklogItemDto backlogItemDto, @RequestHeader("Authorization") String token) {  			
		ProductBacklogItem dao = ProductBacklogItemMapper.mapToDao(backlogItemDto);
		AuditUtil.BaseEntityUpdateFields(dao, token);

		return ProductBacklogItemMapper.mapToDto(repository.save(dao));
	}

	@Override   
	@Transactional
	public UUID create(ProductCreateDto Dto, @RequestHeader("Authorization") String token) {  			  	        
		ProductBacklogItem dao =  ProductBacklogItemMapper.mapToDaoCreate(Dto);
		AuditUtil.BaseEntityCreatedFields(dao, token);

		return  repository.save(dao).getId();
	}                 

	@Override
	@Transactional
	public void deleteByTaskNumber(Integer taskNumber) {
		repository.deleteByTaskNumber(taskNumber);
	}   

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber) {
		Optional<ProductBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);

		return itemOptional.map(ProductBacklogItemMapper::mapToDto);
	}  

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductBacklogItemDto> findById(UUID id) {
		return Optional.of(ProductBacklogItemMapper.mapToDto(repository.findById(id).orElseThrow()));
	} 

	@Override
	@Transactional
	public Optional<ProductBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate, @RequestHeader("Authorization") String token) {		

		return repository.findByTaskNumber(taskNumber)
				.map(dao -> {                    
					ProductBacklogItemMapper.applyUpdateToProduct (dao, taskUpdate);
					AuditUtil.BaseEntityUpdateFields(dao, token);
					return ProductBacklogItemMapper.mapToDto(repository.save(dao));
				});
	}


}
