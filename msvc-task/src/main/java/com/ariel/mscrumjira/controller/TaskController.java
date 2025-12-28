package com.ariel.mscrumjira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/product-to-sprint/{taskNumber}")
    public ResponseEntity<TaskDto> moveFromProductToSprint(@PathVariable Integer taskNumber) {
        TaskDto result = service.moveFromProductToSprint(taskNumber);
        logger.info("Task moved Product→Sprint: taskNumber={}", taskNumber);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/sprint-to-product/{taskNumber}")
    public ResponseEntity<TaskDto> moveFromSprintToProduct(@PathVariable Integer taskNumber) {
        TaskDto result = service.moveFromSprintToProduct(taskNumber);
        logger.info("Task moved Sprint→Product: taskNumber={}", taskNumber);
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
       return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/task-number/{taskNumber}")
    public ResponseEntity<TaskDto> findByTaskNumber(@PathVariable Integer taskNumber){
        logger.info("Fetching SprintBacklogItem with taskNumber={}", taskNumber);
        return  service.findByTaskNumber(taskNumber)
                .map(dto->ResponseEntity.ok(dto) )
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/{taskNumber}/state/{taskState}")
    public ResponseEntity<TaskDto> updateState(@PathVariable Integer taskNumber,  @PathVariable TaskState taskState) {
       logger.info("Updating SprintBacklogItem taskNumber: {} in: {}",taskNumber , taskState);

       return ResponseEntity.ok(service.updateState(taskNumber, taskState));                      
    }  
     @PostMapping 
     public ResponseEntity<TaskDto> create(@RequestBody @Valid ProductCreateDto dto){
        logger.info("creating item ");
         return ResponseEntity.ok(service.create(dto));

     }
}
