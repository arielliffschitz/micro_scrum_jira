package com.ariel.mscrumjira.dto;

import jakarta.validation.constraints.*;

public record MessageCreateDto(
		
		 @NotNull
		 @Email
		 String receiver,
		 
		 @Size(max=100)
		 String subject,		
		
		 @Size(max=10000)
		 String content		
		 
		 ) {

}
