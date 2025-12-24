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

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.service.ProductBacklogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-backlog-items")
public class ProductBacklogController {
    final private ProductBacklogService service;
    private final Logger logger = LoggerFactory.getLogger((ProductBacklogController.class));
    
    public ProductBacklogController(ProductBacklogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity <List<ProductBacklogItemDto>> findAll() {
        logger.info("Fetching all BacklogItems, count={}", service.findAll().size());      
        return ResponseEntity.ok(this.service.findAll()); 
    }   

    @GetMapping("/task-number/{taskNumber}")
    public ResponseEntity<ProductBacklogItemDto> findByTaskNumber(@PathVariable Integer taskNumber)  {        
        logger.info("Fetching ProductBacklogItem with taskNumber={}", taskNumber);
        return   service.findByTaskNumber(taskNumber)
                        .map(dto->ResponseEntity.ok(dto) )
                        .orElseGet(()->ResponseEntity.notFound().build());                   
    }

    @PostMapping
    public ResponseEntity<ProductBacklogItemDto> save(@Valid @RequestBody ProductBacklogItemDto dto) {
    ProductBacklogItemDto saved = service.save(dto);
    logger.info("Created ProductBacklogItem with taskNumber={}", saved.getTaskNumber());
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(service.findByTaskNumber(saved.getTaskNumber())
                         .orElseThrow());
    }

    @PutMapping("/{taskNumber}")
    public ResponseEntity<ProductBacklogItemDto> update(@PathVariable Integer taskNumber, @Valid @RequestBody ProductBacklogItemDto dto) {
        logger.info("Updating ProductBacklogItem: {}", dto);
        return ResponseEntity.ok(service.update(taskNumber, dto));         
    }    

   @DeleteMapping("/task-number/{taskNumber}")
    public ResponseEntity<Void> deleteByTaskNumber(@PathVariable  Integer taskNumber)  {
      logger.info("Deleting ProductBacklogItem taskNumber ID: {}", taskNumber);
      service.deleteByTaskNumber( taskNumber);
      return ResponseEntity.noContent().build();
   }
}
