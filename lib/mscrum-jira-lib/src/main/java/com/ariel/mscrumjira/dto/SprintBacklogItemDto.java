package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.TaskState;

import jakarta.validation.constraints.NotNull;

public class SprintBacklogItemDto {
   
    private UUID id;

    private Integer taskNumber;    

    private String title;
    
    private String description;
    
    private Integer priority;
    
    private Integer estimate;    
    
    @NotNull
    private TaskState taskState;    
    
    private Integer projectKey;    
   
    private Integer sprintKey;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;   
    
    private String createdBy;     

    private LocalDateTime createdAt;
    
    private String updatedBy;   

    private LocalDateTime updatedAt; 

    public SprintBacklogItemDto() {
    }

    

    public SprintBacklogItemDto( Integer taskNumber, String title, String description, Integer priority,
            Integer estimate, Integer projectKey, String createdBy, LocalDateTime createdAt, String updatedBy,LocalDateTime updatedAt) {
        this. taskNumber = taskNumber;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.projectKey = projectKey;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }    

    public SprintBacklogItemDto(  Integer taskNumber, String title, String description, Integer priority,
            Integer estimate, TaskState taskState ,Integer projectKey,Integer sprintKey, LocalDateTime startDate, LocalDateTime endDate, String createdBy,
            LocalDateTime createdAt, String updatedBy,LocalDateTime updatedAt) {      
        this.taskNumber = taskNumber;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.taskState = taskState;
        this.projectKey = projectKey;
        this.sprintKey = sprintKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
   
   public Integer getTaskNumber() {
        return taskNumber;
    }
    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    
    public Integer getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(Integer projectKey) {
		this.projectKey = projectKey;
	}

	public Integer getSprintKey() {
		return sprintKey;
	}

	public void setSprintKey(Integer sprintKey) {
		this.sprintKey = sprintKey;
	}



	public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	public UUID getId() {
        return id;
    }



    public void setId(UUID id) {
        this.id = id;
    }    
    
}
