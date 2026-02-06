package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.domain.enums.AuditTaskState;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskAuditCreateDto;
import com.ariel.mscrumjira.dto.TaskAuditDto;
import com.ariel.mscrumjira.dto.TaskDto;

public class TaskItemMapper {

    private TaskItemMapper(){}

    public static TaskDto toTaskDtoFromSprint(SprintBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
        					dto.getProjectKey(),
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),
                            dto.getTaskState(),                            
                            dto.getSprintKey(),
                            dto.getStartDate(),
                            dto.getEndDate(),
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            dto.getUpdatedBy(),
                            dto.getUpdatedAt()                                                        
                        );
    }
    public static TaskDto toTaskDtoFromProduct(ProductBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
        					dto.getProjectKey(),
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),                           
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            dto.getUpdatedBy(),
                            dto.getUpdatedAt()                                                        
                        );
    }
    public static ProductBacklogItemDto toProductDtoFromSprintDto(SprintBacklogItemDto sprintDto) {
        return new ProductBacklogItemDto(                
                sprintDto.getTitle(),
                sprintDto.getDescription(),
                sprintDto.getPriority(),
                sprintDto.getEstimate(),              
                sprintDto.getCreatedBy(),
                sprintDto.getCreatedAt(),
                sprintDto.getUpdatedBy(),
                sprintDto.getUpdatedAt(),
                sprintDto.getTaskNumber(),
                sprintDto.getProjectKey()
        );
    }

    public static SprintBacklogItemDto toSprintDtoFromProductDto(ProductBacklogItemDto productDto) {
        return new SprintBacklogItemDto(
                productDto.getTaskNumber(),                
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getPriority(),
                productDto.getEstimate(),  
                productDto.getProjectKey(),
                productDto.getCreatedBy(),
                productDto.getCreatedAt(),
                productDto.getUpdatedBy(),
                productDto.getUpdatedAt()
                
        );
    }   
    
    public static AuditTaskState toAuditTaskStateFromTaskState(TaskState taskState) {
    	return switch (taskState) {
    	case BLOCKED -> AuditTaskState.BLOCKED;
    	case IN_PROGRESS -> AuditTaskState.IN_PROGRESS;
    	case ARCHIVED -> AuditTaskState.ARCHIVED;
    	case DONE -> AuditTaskState.DONE;
    	case REVIEW -> AuditTaskState.REVIEW;
    	case CANCELED -> AuditTaskState.CANCELED;
    	case ON_HOLD -> AuditTaskState.ON_HOLD;		

    	default -> throw new IllegalArgumentException("Unknown TaskState: " + taskState);
    	};
    }

	public static  TaskAuditCreateDto toTaskCreateDtoFromTaskDto(TaskDto taskDto) {
		
		return new TaskAuditCreateDto(
				taskDto.getTaskNumber(),
				taskDto.getProjectKey(),
				taskDto.getTitle(),
				taskDto.getDescription(),
				taskDto.getEstimate(),
				taskDto.getSprintKey(),
				taskDto.getCreatedBy(),
				taskDto.getCreatedAt()
				
				);
		
	}

	public static TaskDto toTaskDtoFromAudit(TaskAuditDto dto) {
		 return new TaskDto(
				 dto.getTaskNumber(), 
				 dto.getProjectKey(),
                 dto.getTitle(),
                 dto.getDescription(),                 
                 dto.getEstimate(), 
                 TaskState.ARCHIVED,
                 dto.getCreatedBy(),
                 dto.getCreatedAt()                                                                     
             );
	}          
   
}
