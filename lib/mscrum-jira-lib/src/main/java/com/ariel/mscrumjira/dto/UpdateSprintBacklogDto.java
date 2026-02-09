package com.ariel.mscrumjira.dto;

import com.ariel.mscrumjira.domain.enums.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateSprintBacklogDto (

		@NotBlank
		@Size(max=50)
		String title,

		@NotBlank
		@Size(max=250)
		String description, 

		@Min(1)
		@Max(10) 
		Integer priority, 

		@Positive   
		Integer estimate,

		TaskState taskState
		) {        
	public UpdateSprintBacklogDto(TaskState taskState) {
		this( null, null,null,null,taskState);
	}	
}
