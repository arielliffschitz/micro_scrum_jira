package com.ariel.mscrumjira.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.service.SprintBacklogItemService;
@RestController
@RequestMapping("/sprint-backlog-items")
public class SprintBacklogItemController {
    private final Logger logger = LoggerFactory.getLogger(SprintBacklogItemController.class);
    private final SprintBacklogItemService service;

    public SprintBacklogItemController(SprintBacklogItemService service) {
        this.service = service;
}

    @PostMapping
    public ResponseEntity<SprintBacklogItemDto> save (@RequestBody SprintBacklogItemDto dto) {  
        logger.info("Creating sprintItem taskNumber: {}", dto.getTaskNumber());
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public ResponseEntity <List<SprintBacklogItemDto>>list() {
        logger.info("Fetching all SprintBacklogItems, count={}", service.findAll().size());      
        return ResponseEntity.ok(service.findAll());
    }    

    @GetMapping("/task-number/{taskNumber}")
    public ResponseEntity<SprintBacklogItemDto> findByTaskNumber(@PathVariable Integer taskNumber)  {        
        logger.info("Fetching SprintBacklogItem with taskNumber={}", taskNumber);
        return  service.findByTaskNumber(taskNumber)
                .map(dto->ResponseEntity.ok(dto) )
                .orElseGet(()->ResponseEntity.notFound().build());                   
    }

    @PutMapping("/{taskNumber}/state/{taskState}")
    public ResponseEntity<SprintBacklogItemDto> updateState(@PathVariable Integer taskNumber,  @PathVariable TaskState taskState) {
       logger.info("Updating SprintBacklogItem taskNumber: {} in: {}",taskNumber , taskState);

       return service.updateState(taskNumber, taskState)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());         
    }  

    @DeleteMapping("/task-number/{taskNumber}")
     public ResponseEntity<Void> deleteByTaskNumber(@PathVariable  Integer taskNumber)  {
       logger.info("Deleting SprintBacklogItem taskNumber : {}", taskNumber);
       service.deleteByTaskNumber( taskNumber);
       return ResponseEntity.noContent().build();
   }   
}
