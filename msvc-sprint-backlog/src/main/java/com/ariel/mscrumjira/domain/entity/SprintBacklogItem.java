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
public class SprintBacklogItem {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "product_backlog_id")
    private UUID productBacklogId;

    private String title;
    
    private String description;
    
    private Integer priority;
    
    private Integer estimate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "task_state")
    private TaskState taskState;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;


}
