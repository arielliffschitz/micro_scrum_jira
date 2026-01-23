package com.ariel.scrumjira.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.RoleDto;
import com.ariel.scrumjira.dto.RoleCreateDto;

import com.ariel.scrumjira.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
	 private final RoleService service;
	 private final Logger logger = LoggerFactory.getLogger(RoleController.class);
	 
	 public RoleController(RoleService service) {		
		this.service = service;
	 }
	
	 @GetMapping
	 public ResponseEntity <List<RoleDto> >list() {
	        logger.info("Listing roles");
	        return ResponseEntity.ok(service.findAll());
	 }
	 
	 @PostMapping
	 public ResponseEntity<RoleDto> create(@Valid@RequestBody RoleCreateDto roleCreateDto, @RequestHeader("Authorization") String token) {   
	        logger.info("RoleController::create: {}", roleCreateDto);         
	        return ResponseEntity.ok(service.save(roleCreateDto, token));
	 }
	 @DeleteMapping("/role-name/{name}")
	 public ResponseEntity<?> deleteByUsername(@PathVariable RoleName name){
	    	service.deleteByName(name);
	    	return ResponseEntity.noContent().build();
	 }
	 
}
