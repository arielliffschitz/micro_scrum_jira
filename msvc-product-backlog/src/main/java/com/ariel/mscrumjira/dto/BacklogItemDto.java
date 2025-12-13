package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;


public class BacklogItemDto {

    private String title;    
    private String description;    
    private Integer priority;    
    private Integer estimate;    
    private String createdBy;        
    private LocalDateTime createdAt;          

    public BacklogItemDto() {
    }

    public BacklogItemDto(String title, String description, Integer priority, Integer estimate, String createdBy,
            LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
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

    

    
}
