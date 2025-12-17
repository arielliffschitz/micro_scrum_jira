package com.ariel.mscrumjira.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <List<ProductBacklogItemDto>>list() {
        logger.info("Fetching all BacklogItems, count={}", service.findAll().size());      
        return ResponseEntity.ok(this.service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogItemDto> details(@PathVariable UUID id)  {        
        logger.info("Fetching BacklogItem with id={}", id);
        return  service.findById(id)
                .map(dto->ResponseEntity.ok(dto) )
                .orElseGet(()->ResponseEntity.notFound().build());                   
    }
    @PostMapping
    public ResponseEntity<ProductBacklogItemDto> create(@Valid @RequestBody ProductBacklogItemDto dto) {
        logger.info("Creating product: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklogItemDto> update(@PathVariable UUID id, @RequestBody ProductBacklogItemDto dto) {
        logger.info("Updating BacklogItem: {}", dto);
        return ResponseEntity.ok(service.update(id, dto));
         
    }        
}
