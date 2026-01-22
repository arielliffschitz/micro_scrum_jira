package com.ariel.scrumjira.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ariel.mscrumjira.dto.UserDto;
import com.ariel.mscrumjira.dto.UserLoginDto;
import com.ariel.scrumjira.dto.UserCreateDto;
import com.ariel.scrumjira.dto.UserUpdateDto;
import com.ariel.scrumjira.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {
    private final UserService service;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    public UserController(UserService service) {
        this.service = service;       
    }    
    
    @GetMapping
    public ResponseEntity <List<UserDto> >list() {
        logger.info("Listing users");
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username){
        logger.info("Fetching User with username={}", username);
        return  service.findByUsername(username)
                .map(dto->ResponseEntity.ok(dto) )
                .orElseGet(()->ResponseEntity.notFound().build());
    } 
    
    @GetMapping("/user-login/{username}")
    public ResponseEntity<UserLoginDto> findForLoginByUsername(@PathVariable String username){
        logger.info("Fetching User with username={}", username);
        return  service.findForLoginByUsername(username)
                .map(ResponseEntity::ok )
                .orElseGet(()->ResponseEntity.notFound().build());
    }       
    
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto userCreateDto) {   
        logger.info("UseController::create: {}", userCreateDto);         
        return ResponseEntity.ok(service.create(userCreateDto));
    }
    
    @DeleteMapping("/username/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable String username){
    	service.deleteByUsername(username);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/username/{username}")
    public ResponseEntity<UserDto> update(@PathVariable String username, @RequestBody UserUpdateDto userUpdateDto ) {
    	logger.info("Updating user with username: {} in: {}",username , userUpdateDto);
        return ResponseEntity.ok(service.update(username, userUpdateDto));                      	        
        
    }
}
