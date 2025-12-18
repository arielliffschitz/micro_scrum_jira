package com.ariel.mscrumjira.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.service.SprintBacklogItemService;

@RestController
@RequestMapping("/sprint-backlog-items")
public class SprintBacklogItemController {

    private final SprintBacklogItemService service;

    public SprintBacklogItemController(SprintBacklogItemService service) {
        this.service = service;
}

     @PostMapping("/{id}")
     public SprintBacklogItemDto create(@PathVariable UUID id){
        return service.create(id);
     }

}
