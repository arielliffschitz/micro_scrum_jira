package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record SprintCreateDto (
       
		@NotNull
		Integer projectKey,		   	    
		
		@NotNull			
		String teamKey,
		
	    @NotNull		
	    LocalDateTime startDate,

		@NotNull	   
		LocalDateTime endDate

		){

}
