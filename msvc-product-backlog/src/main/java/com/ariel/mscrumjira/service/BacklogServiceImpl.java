package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.dto.BacklogItemDto;
import com.ariel.mscrumjira.entity.BacklogItem;
import com.ariel.mscrumjira.repository.BacklogRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BacklogServiceImpl implements BacklogService {

    final private BacklogRepository repository;

    public BacklogServiceImpl(BacklogRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemDto> findAll() {
        return ((List<BacklogItem>) repository.findAll())
                .stream()
                .map(this:: mapToDto)
                .collect(Collectors.toList());        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BacklogItemDto> findById(Long id) {
       Optional<BacklogItem> itemOptional = repository.findById(id);
       return itemOptional.map(this::mapToDto);
    }

    @Override
    @Transactional
    public BacklogItemDto save(BacklogItemDto backlogItemDto) {
       return mapToDto(repository.save(mapToDao(backlogItemDto)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    private BacklogItemDto mapToDto(BacklogItem item){
        return  new BacklogItemDto(                   
                                        item.getTitle(),
                                        item.getDescription(),                   
                                        item.getPriority(),
                                        item.getEstimate(),
                                        item.getCreatedBy(),
                                        item.getCreatedAt()
                    );
    }
    private BacklogItem mapToDao(BacklogItemDto itemDto){
        return  new BacklogItem(                   
                                        itemDto.getTitle(),
                                        itemDto.getDescription(),                   
                                        itemDto.getPriority(),
                                        itemDto.getEstimate(),
                                        itemDto.getCreatedBy(),
                                        itemDto.getCreatedAt()
                    );
    }

}
