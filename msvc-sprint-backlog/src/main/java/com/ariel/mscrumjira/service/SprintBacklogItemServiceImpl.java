package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

//import feign.FeignException;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {

    private ProductBacklogFeignClient client;

   
    public SprintBacklogItemServiceImpl(ProductBacklogFeignClient client) {
        this.client = client;
    }

    @Override
    public List<SprintBacklogItemDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<SprintBacklogItemDto> findSprintById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSprintById'");
    }

    @Override
    public Optional<SprintBacklogItemDto> updateTaskState(UUID id, TaskState taskState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTaskState'");
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public SprintBacklogItemDto create(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ProductBacklogItemDto findProductById(UUID id) {
        
           return client.findProductById(id);
        
    }

    


}
