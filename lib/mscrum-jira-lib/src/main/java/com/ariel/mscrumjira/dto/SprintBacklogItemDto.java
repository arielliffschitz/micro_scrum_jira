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
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;   
    
    private String createdBy;     

    private LocalDateTime createdAt;

    public SprintBacklogItemDto() {
    }

    

    public SprintBacklogItemDto( Integer taskNumber, String title, String description, Integer priority,
            Integer estimate, String createdBy, LocalDateTime createdAt) {
        this. taskNumber = taskNumber;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }    

    public SprintBacklogItemDto(  Integer taskNumber, String title, String description, Integer priority,
            Integer estimate, TaskState taskState, LocalDateTime startDate, LocalDateTime endDate, String createdBy,
            LocalDateTime createdAt) {      
        this.taskNumber = taskNumber;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.taskState = taskState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
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



    public UUID getId() {
        return id;
    }



    public void setId(UUID id) {
        this.id = id;
    }    
    
}
