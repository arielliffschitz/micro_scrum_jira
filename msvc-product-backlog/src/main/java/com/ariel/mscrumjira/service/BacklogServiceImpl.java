package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.dto.BacklogItemDto;
import com.ariel.mscrumjira.entity.BacklogItem;
import com.ariel.mscrumjira.repository.BacklogRepository;

@Service
public class BacklogServiceImpl implements BacklogService {

    final private BacklogRepository repository;

    public BacklogServiceImpl(BacklogRepository repository){
        this.repository = repository;
    }

    @Override
    public List<BacklogItemDto> findAll() {
        return ((List<BacklogItem>) repository.findAll())
                .stream()
                .map(item-> new BacklogItemDto(                   
                                        item.getTitle(),
                                        item.getDescription(),                   
                                        item.getPriority(),
                                        item.getEstimate(),
                                        item.getCreatedBy(),
                                        LocalDateTime.now()
                    )).collect(Collectors.toList());        
    }

    @Override
    public Optional<BacklogItemDto> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public BacklogItemDto save(BacklogItemDto backlogItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
    

}
