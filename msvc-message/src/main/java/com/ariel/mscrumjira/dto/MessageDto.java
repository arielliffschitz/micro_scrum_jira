package com.ariel.mscrumjira.dto;

import java.time.*;


public record MessageDto(
		
		Integer messageKey,
		
		 String receiver,		 		 
		 
		 String subject,
		
		 String content,
		
		 boolean readFlag,
		 
		 String createdBy,   

		 LocalDateTime createdAt
		
		) {

}
