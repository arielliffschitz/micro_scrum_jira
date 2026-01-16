package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;

public class TaskItemMapper {

    private TaskItemMapper(){}

    public static TaskDto toTaskDtoFromSprint(SprintBacklogItemDto dto) {
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
                            dto.getUpdatedBy(),
                            dto.getUpdatedAt(),
                            Boolean.TRUE          
                        );
    }
    public static TaskDto toTaskDtoFromProduct(ProductBacklogItemDto dto) {
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
                            dto.getUpdatedBy(),
                            dto.getUpdatedAt(),
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
                sprintDto.getUpdatedBy(),
                sprintDto.getUpdatedAt(),
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
                productDto.getCreatedAt(),
                productDto.getUpdatedBy(),
                productDto.getUpdatedAt()
        );
    }          
   
}
