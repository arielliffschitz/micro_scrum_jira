package com.ariel.mscrumjira.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_backlog_items")
public class ProductBacklogItem extends BaseEntity{
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name = "task_number", nullable = false, unique = true)
    private Integer taskNumber;


    private String title;
    
    private String description;
    
    private Integer priority;
    
    private Integer estimate;     
    

    public ProductBacklogItem() {
    }

    public ProductBacklogItem(String title, String description, Integer priority, Integer estimate ,
    							String createdBy, LocalDateTime createdAt, Integer taskNumber) {
    	super(createdBy,createdAt);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;         
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
        this.estimate = estimate;    }
   

    public UUID getId() {
        return id;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

   

    
}
