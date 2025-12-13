package com.ariel.mscrumjira.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.service.BacklogService;

@RestController
@RequestMapping("/backlog-items")
public class BacklogController {
    final private BacklogService service;
    private final Logger logger = LoggerFactory.getLogger((BacklogController.class));
    
    public BacklogController(BacklogService service) {
        this.service = service;
    }
     @GetMapping
    public ResponseEntity<?> list() {
        logger.info("Call BacklogController::list()");       
        return ResponseEntity.ok(this.service.findAll());
    }

}
