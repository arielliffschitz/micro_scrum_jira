package com.ariel.mscrumjira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/product-to-sprint/{taskNumber}")
    public ResponseEntity<SprintBacklogItemDto> moveFromProductToSprint(@PathVariable Integer taskNumber) {
        SprintBacklogItemDto result = service.moveFromProductToSprint(taskNumber);
        logger.info("Task moved Product→Sprint: taskNumber={}", taskNumber);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/sprint-to-product/{taskNumber}")
    public ResponseEntity<ProductBacklogItemDto> moveFromSprintToProduct(@PathVariable Integer taskNumber) {
        ProductBacklogItemDto result = service.moveFromSprintToProduct(taskNumber);
        logger.info("Task moved Sprint→Product: taskNumber={}", taskNumber);
        return ResponseEntity.ok(result);
    }
}
