package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public class ProductBacklogItemMapper {
    
     public static ProductBacklogItemDto mapToDto(ProductBacklogItem item){
        return  new ProductBacklogItemDto(                                                 
                    item.getTitle(),
                    item.getDescription(),                   
                    item.getPriority(),
                    item.getEstimate(),
                    item.getCreatedBy(),
                    item.getCreatedAt(),
                    item.getUpdatedBy(),
                    item.getUpdatedAt(),
                    item.getTaskNumber(),
                    item.getProjectKey()
                );
    }

     public static ProductBacklogItem mapToDao(ProductBacklogItemDto itemDto){
        return  new ProductBacklogItem(                   
                    itemDto.getTitle(),
                    itemDto.getDescription(),                   
                    itemDto.getPriority(),
                    itemDto.getEstimate(),
                    itemDto.getCreatedBy(),
                    itemDto.getCreatedAt(),
                    itemDto.getTaskNumber(),
                    itemDto.getProjectKey()
                );
    }
    public static ProductBacklogItem mapToDaoCreate(ProductCreateDto itemDto){
        ProductBacklogItem dao =  new ProductBacklogItem();                           
                           dao.setTitle(itemDto.title());
                           dao.setDescription(itemDto.description());                  
                           dao.setPriority(itemDto.priority());
                           dao.setEstimate(itemDto.estimate()); 
                           dao.setProjectKey(itemDto.projectKey());
        return dao;
    }
     public static void applyUpdateToProduct(ProductBacklogItem currentTask, UpdateDto taskUpdate) {
        if (taskUpdate.getTitle() !=null) 
                                currentTask.setTitle(taskUpdate.getTitle());
        if (taskUpdate.getDescription() !=null) 
                            currentTask.setDescription(taskUpdate.getDescription());
        if (taskUpdate.getPriority() !=null) 
                            currentTask.setPriority(taskUpdate.getPriority());
        if (taskUpdate.getEstimate() !=null) 
                            currentTask.setEstimate(taskUpdate.getEstimate());
       

    }
}
