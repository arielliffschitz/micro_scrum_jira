package com.ariel.mscrumjira.dto;

import java.time.*;


public record MessageListDto(
		
		Integer messageKey,
		
		 String receiver,		 		 
		 
		 String subject,				
		
		 boolean readFlag,
		 
		 String createdBy,   

		 LocalDateTime createdAt
		
		) {

}
