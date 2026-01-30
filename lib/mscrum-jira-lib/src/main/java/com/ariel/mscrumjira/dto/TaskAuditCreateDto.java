package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

public record TaskAuditCreateDto ( 

	 Integer taskNumber,	 
	 Integer projectKey,
	 String title,
	 String description,	
	 Integer estimate,
	 Integer sprintKey,              	
	 String createdBy,
	 LocalDateTime createdAt
	)	
 {}
