package com.ariel.mscrumjira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
@RequestMapping("/task")
public class TaskController {
    final private TaskService service;
    private final Logger logger = LoggerFactory.getLogger((TaskController.class));


    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/move-product-to-sprint/{taskNumber}")
    public ResponseEntity<SprintBacklogItemDto> moveFromProductToSprint(@PathVariable Integer taskNumber) {                
        return ResponseEntity.ok( service.moveFromProductToSprint(taskNumber));                       
    }           
}
