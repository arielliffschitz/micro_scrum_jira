package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

//import feign.FeignException;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {

    private ProductBacklogFeignClient client;
    private SprintBacklogItemRepository repository;   
    

    public SprintBacklogItemServiceImpl(ProductBacklogFeignClient client, SprintBacklogItemRepository repository) {
        this.client = client;
        this.repository = repository;
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
    public void deleteSprintById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public SprintBacklogItemDto create(UUID productBacklogId) {

       ProductBacklogItemDto productBacklogItemDto = findProductById( productBacklogId);     

       SprintBacklogItem     daoSprint  =  repository.save(mapFromProductDtoToSprintDao(productBacklogItemDto));

       deleteProductById( productBacklogId);

       return mapToDto(daoSprint);
    }

    private SprintBacklogItem mapFromProductDtoToSprintDao(ProductBacklogItemDto productBacklogItemDto) {
       
        return new SprintBacklogItem(
                                        productBacklogItemDto.getId(),
                                        productBacklogItemDto.getTitle(),
                                        productBacklogItemDto.getDescription(),                   
                                        productBacklogItemDto.getPriority(),
                                        productBacklogItemDto.getEstimate(),
                                        TaskState.PENDING
        );
    }

    private ProductBacklogItemDto findProductById(UUID productBacklogId) {
        
           return client.findProductById(productBacklogId);        
    }    

   
    private void deleteProductById(UUID productBacklogId){
        client.deleteProductById(productBacklogId);
    }
    private SprintBacklogItemDto mapToDto(SprintBacklogItem daoSprint) {
        return  new SprintBacklogItemDto(  
                                        daoSprint.getSprintBacklogId(),
                                        daoSprint.getProductBacklogId(),
                                        daoSprint.getTitle(),
                                        daoSprint.getDescription(),                   
                                        daoSprint.getPriority(),  
                                        daoSprint.getEstimate(),                                        
                                        daoSprint.getTaskState(),
                                        daoSprint.getStartDate(),
                                        daoSprint.getEndDate(),
                                        daoSprint.getCreatedBy(),
                                        daoSprint.getCreatedAt()
                        );
    }          

    

    


}
