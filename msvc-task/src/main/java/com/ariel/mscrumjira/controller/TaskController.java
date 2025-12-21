package com.ariel.mscrumjira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/task")
public class TaskController {
    final private TaskService service;
    private final Logger logger = LoggerFactory.getLogger((TaskController.class));


    public TaskController(TaskService service) {
        this.service = service;
    }
    
    @GetMapping("/task-number/{taskNumber}")
    public ResponseEntity<ProductBacklogItemDto> test(@PathVariable Integer taskNumber) {
        logger.info("Fetching ProductBacklogItem with taskNumber={}", taskNumber);
        return  ResponseEntity.ok(service.test(taskNumber));
                //.map(dto->ResponseEntity.ok(dto) )
                //.orElseGet(()->ResponseEntity.notFound().build());  
    }
    

}
