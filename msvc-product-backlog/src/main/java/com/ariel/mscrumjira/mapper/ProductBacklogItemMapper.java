package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateSprintBacklogDto;

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
     public static void applyUpdateToProduct(ProductBacklogItem currentTask, UpdateSprintBacklogDto taskUpdate) {
        if (taskUpdate.title() !=null) 
                                currentTask.setTitle(taskUpdate.title());
        if (taskUpdate.description() !=null) 
                            currentTask.setDescription(taskUpdate.description());
        if (taskUpdate.priority() !=null) 
                            currentTask.setPriority(taskUpdate.priority());
        if (taskUpdate.estimate() !=null) 
                            currentTask.setEstimate(taskUpdate.estimate());
       

    }
}
