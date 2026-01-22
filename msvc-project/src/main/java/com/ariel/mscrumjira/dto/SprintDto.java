package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.enums.SprintState;
import com.ariel.mscrumjira.entity.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record SprintDto (	

		Integer sprintKey, 	    

		@JsonIgnoreProperties({"sprints"})		
		Project project,	   	    				

		String teamKey,	  

		SprintState state,		

		LocalDateTime startDate,

		LocalDateTime endDate,

		String createdBy,   

		LocalDateTime createdAt,   

		String updatedBy,   

		LocalDateTime updatedAt

		)		    	    
{}
