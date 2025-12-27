package com.ariel.mscrumjira.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.service.SprintBacklogItemService;
@RestController
@RequestMapping("/sprint-backlog-items")
public class SprintBacklogItemController {   
    private final SprintBacklogItemService service;

    public SprintBacklogItemController(SprintBacklogItemService service) {
        this.service = service;
}

    @PostMapping
    public SprintBacklogItemDto save (@RequestBody SprintBacklogItemDto dto) {         
        return service.save(dto);
    }

    @GetMapping
    public List<SprintBacklogItemDto>list() {              
        return service.findAll();
    }    

    @GetMapping("/task-number/{taskNumber}")
    public SprintBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber)  {               
       return service.findByTaskNumber(taskNumber)
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));                  
    }

    @PutMapping("/{taskNumber}/state/{taskState}")
    public SprintBacklogItemDto updateState(@PathVariable Integer taskNumber,  @PathVariable TaskState taskState) {     
       return service.updateState(taskNumber, taskState) 
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));                          
    }  

    @DeleteMapping("/task-number/{taskNumber}")
     public void deleteByTaskNumber(@PathVariable  Integer taskNumber)  {     
       service.deleteByTaskNumber( taskNumber);      
   }   
}
