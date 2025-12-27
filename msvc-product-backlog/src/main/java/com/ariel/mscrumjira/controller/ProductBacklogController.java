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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.service.ProductBacklogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-backlog-items")
public class ProductBacklogController {
    final private ProductBacklogService service;   
    
    public ProductBacklogController(ProductBacklogService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductBacklogItemDto> findAll() {       
        return service.findAll(); 
    }   

    @GetMapping("/task-number/{taskNumber}")
    public ProductBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber)  {            
        return   service.findByTaskNumber(taskNumber)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));                  
    }

    @PostMapping
    public ProductBacklogItemDto save(@Valid @RequestBody ProductBacklogItemDto dto) {        
        return service.save(dto);
    }    

   @DeleteMapping("/task-number/{taskNumber}")
    public void deleteByTaskNumber(@PathVariable  Integer taskNumber)  {      
      service.deleteByTaskNumber( taskNumber);      
   }
}
