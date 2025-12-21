package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;



@Service
public class TaskServiceImpl implements TaskService{    
    
    private final ProductBacklogFeignClient clientProduct;

    
    public TaskServiceImpl(ProductBacklogFeignClient clientProduct) {
        this.clientProduct = clientProduct;
    }

    @Override
   
    public List<SprintBacklogItemDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override

    public Optional<SprintBacklogItemDto> findSprintByTaskNumber(Integer taskNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTaskNumber'");
    }

    @Override
    
    public SprintBacklogItemDto moveToSprint(Integer taskNumber) {
        // ProductBacklogItemDto productDto = clientProduct.findByTaskNumber(taskNumber);

       /*  SprintBacklogItem daoSprint = repository.save(mapFromProductDtoToSprintDao(productDto));

        deleteProductById(productBacklogId);

        return mapToDto(daoSprint); */
        return null;
    }
   
 @Override
    public ProductBacklogItemDto test(Integer taskNumber) {
         return clientProduct.findProductByTaskNumber(taskNumber);          
    }


    @Override
    public void moveToProduct(Integer taskNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveToProduct'");
    }

    @Override
    public Optional<ProductBacklogItemDto> findProductByTaskNumber(Integer taskNumber) {
         throw new UnsupportedOperationException("Unimplemented method 'moveToProduct'");
    }

}
