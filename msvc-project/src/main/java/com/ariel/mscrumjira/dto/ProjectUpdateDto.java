package com.ariel.mscrumjira.dto;

import com.ariel.mscrumjira.domain.enums.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectUpdateDto (

	 @NotBlank
	 @Size(max=50)
      String name,

	 @Size(max=250)
     String description,    
     
     ProjectState state
	 )
{ 	 
}
