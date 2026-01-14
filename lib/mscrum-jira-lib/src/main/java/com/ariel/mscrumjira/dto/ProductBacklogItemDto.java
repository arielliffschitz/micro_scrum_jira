package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductBacklogItemDto {  

    private UUID id;
    
    @NotBlank
    @Size(max=50)
    private String title;  

    @Size(max=250)
    private String description; 

    @NotNull  
    @Min(1)
    @Max(10) 
    private Integer priority; 

    @NotNull
    @Positive   
    private Integer estimate;  

    private String createdBy;   

    private LocalDateTime createdAt;   
    
    private Integer taskNumber;

    public ProductBacklogItemDto() {
    }

    public ProductBacklogItemDto( String title, String description, Integer priority, Integer estimate, String createdBy,
            LocalDateTime createdAt, Integer taskNumber) {        
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
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

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    

    

    
}
