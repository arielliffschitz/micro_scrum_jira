package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
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
                            .map(this::mapToDto)
                            .collect(Collectors.toList());        
    }   

    @Override
    @Transactional
    public ProductBacklogItemDto save(ProductBacklogItemDto backlogItemDto) {
       return mapToDto(repository.save(mapToDao(backlogItemDto)));
    }   

    @Override
    @Transactional
    public void deleteByTaskNumber(Integer taskNumber) {
       repository.deleteByTaskNumber(taskNumber);
     }   

    @Override
     public Optional<ProductBacklogItemDto> findByTaskNumber(Integer taskNumber) {
       Optional<ProductBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);
       return itemOptional.map(this::mapToDto);
     }      

    private ProductBacklogItemDto mapToDto(ProductBacklogItem item){
        return  new ProductBacklogItemDto(                                                 
                    item.getTitle(),
                    item.getDescription(),                   
                    item.getPriority(),
                    item.getEstimate(),
                    item.getCreatedBy(),
                    item.getCreatedAt(),
                    item.getTaskNumber()
                );
    }

     private ProductBacklogItem mapToDao(ProductBacklogItemDto itemDto){
        return  new ProductBacklogItem(                   
                    itemDto.getTitle(),
                    itemDto.getDescription(),                   
                    itemDto.getPriority(),
                    itemDto.getEstimate(),
                    itemDto.getTaskNumber()                                    
                );
    }         
}
 