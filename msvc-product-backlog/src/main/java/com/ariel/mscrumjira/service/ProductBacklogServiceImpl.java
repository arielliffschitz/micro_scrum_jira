package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.mapper.ProductBacklogItemMapper;
import com.ariel.mscrumjira.repository.ProductBacklogRepository;

import org.springframework.transaction.annotation.Transactional;

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
    public ProductBacklogItemDto save(ProductBacklogItemDto backlogItemDto) {                
        return ProductBacklogItemMapper
                .mapToDto(repository.save(ProductBacklogItemMapper.mapToDao(backlogItemDto)));
    }
    @Override   
    @Transactional
    public UUID create(ProductCreateDto Dto) {
        return  repository.save(ProductBacklogItemMapper.mapToDaoCreate(Dto)).getId();
    }                 

    @Override
    @Transactional
    public void deleteByTaskNumber(Integer taskNumber) {
       repository.deleteByTaskNumber(taskNumber);
     }   

    @Override
     public Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber) {
       Optional<ProductBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);
       return itemOptional.map(ProductBacklogItemMapper::mapToDto);
     }      
    @Override
     public Optional<ProductBacklogItemDto> findById(UUID id) {
        return Optional.of(ProductBacklogItemMapper.mapToDto(repository.findById(id).orElseThrow()));
     }         
   
  
}
 