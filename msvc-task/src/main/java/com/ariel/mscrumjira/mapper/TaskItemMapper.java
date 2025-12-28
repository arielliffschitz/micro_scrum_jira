package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;

public class TaskItemMapper {

    private TaskItemMapper(){}

    public static TaskDto mapSprintToTask(SprintBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),
                            dto.getTaskState(),
                            dto.getStartDate(),
                            dto.getEndDate(),
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            Boolean.TRUE          
                        );
    }
    public static TaskDto mapProductToTask(ProductBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),
                            null,
                            null,
                            null,
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            Boolean.FALSE          
                        );
    }
    public static ProductBacklogItemDto mapFromSprintDtoToProductDto(SprintBacklogItemDto sprintDto) {
        return new ProductBacklogItemDto(                
                sprintDto.getTitle(),
                sprintDto.getDescription(),
                sprintDto.getPriority(),
                sprintDto.getEstimate(),              
                sprintDto.getCreatedBy(),
                sprintDto.getCreatedAt(),
                sprintDto.getTaskNumber()
        );
    }


    public static SprintBacklogItemDto mapFromProductDtoToSprintDto(ProductBacklogItemDto productDto) {
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

    /* public static ProductBacklogItemDto mapCreateToProductDto(ProductCreateDto createDto) {
        ProductBacklogItemDto productDto  =new ProductBacklogItemDto();               
                productDto.setTitle(createDto.title());
                productDto.setDescription(createDto.description());
                productDto.setPriority(createDto.priority());
                productDto.setEstimate(createDto.estimate());                            
         return productDto;
    }    */
}
