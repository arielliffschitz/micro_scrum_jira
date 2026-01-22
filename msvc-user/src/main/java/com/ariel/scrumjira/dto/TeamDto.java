package com.ariel.scrumjira.dto;

import java.time.LocalDateTime;

public record TeamDto (
	
	   String teamKey,		
		
	   String username,					 
	  
	   String createdBy,   

	   LocalDateTime createdAt,   
	    
	   String updatedBy,   

	   LocalDateTime updatedAt

	  )
	 

{}
