package com.ariel.mscrumjira.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.service.SprintBacklogItemService;

@RestController
@RequestMapping("/sprint-backlog-items")
public class SprintBacklogItemController {
private final SprintBacklogItemService service;


    public SprintBacklogItemController(SprintBacklogItemService service) {
        this.service = service;
}


     @GetMapping("/{id}")
     public ProductBacklogItemDto findProductById(@PathVariable UUID id){
        return service.findProductById(id);
     }

}
