package com.ariel.mscrumjira.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.service.ProductBacklogService;

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
	public ProductBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber){    	    	
		return   service.findByTaskNumber(taskNumber)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));                  
	}

	@PostMapping("/move")
	public ProductBacklogItemDto save( @RequestBody ProductBacklogItemDto dto, @RequestHeader("Authorization") String token) {        
		return service.save(dto, token);
	}   
	@PostMapping
	public ProductBacklogItemDto create( @RequestBody ProductCreateDto dto, @RequestHeader("Authorization") String token) { 
		UUID id = service.create(dto, token);
		return service.findById(id);				
	}     

	@PutMapping("/task-number/{taskNumber}")
	public  ProductBacklogItemDto update(@PathVariable Integer taskNumber, @RequestBody UpdateDto taskUpdate,
			@RequestHeader("Authorization") String token){
		return service.update(taskNumber, taskUpdate, token) 
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));   
	}

	@DeleteMapping("/task-number/{taskNumber}")
	public void deleteByTaskNumber(@PathVariable  Integer taskNumber)  {      
		service.deleteByTaskNumber( taskNumber);      
	}
}
