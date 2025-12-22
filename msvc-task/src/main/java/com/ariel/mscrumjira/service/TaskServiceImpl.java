package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.client.SprintBacklogFeignClient;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
@Service
public class TaskServiceImpl implements TaskService{    
    
    private final ProductBacklogFeignClient clientProduct;
    private final SprintBacklogFeignClient  clientSprint;        


    public TaskServiceImpl(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint) {
        this.clientProduct = clientProduct;
        this.clientSprint = clientSprint;
    }


    @Override
    public List<ProductBacklogItemDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public Optional<ProductBacklogItemDto> findTaskByTaskNumber(Integer taskNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findTaskByTaskNumber'");
    }


    @Override
    public SprintBacklogItemDto moveFromProductToSprint(Integer taskNumber) {
        ProductBacklogItemDto productDto = clientProduct.findTaskByTaskNumber(taskNumber);
        SprintBacklogItemDto  SprintDto  = clientSprint.save(mapFromProductDtoToSprintDto(productDto));
        clientProduct.deleteProductByTaskNumber(taskNumber);
        return SprintDto;
    }

    @Override
    public ProductBacklogItemDto moveFromSprintToProduct(Integer taskNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveFromSprintToProduct'");
    }

    private SprintBacklogItemDto mapFromProductDtoToSprintDto(ProductBacklogItemDto productDto) {
        return new SprintBacklogItemDto(
                productDto.getTaskNumber(),
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getPriority(),
                productDto.getEstimate(),              
                productDto.getCreatedBy(),
                productDto.getCreatedAt()
                
        );
    }
}
