package com.ariel.mscrumjira.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ariel.mscrumjira.domain.enums.TaskState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sprint_backlog_items")
public class SprintBacklogItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "task_number")
    private Integer taskNumber;

    private String title;
    
    private String description;
    
    private Integer priority;
    
    private Integer estimate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "task_state")
    private TaskState taskState;        

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;    
    
    public SprintBacklogItem() {
    }        

    public SprintBacklogItem(Integer taskNumber, String title, String description, Integer priority,
            Integer estimate,String createdBy, LocalDateTime createdAt, TaskState taskState) {
    	super(createdBy,createdAt);
        this. taskNumber =  taskNumber;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimate = estimate;
        this.taskState = taskState;
    }   
    
     public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

     
}
