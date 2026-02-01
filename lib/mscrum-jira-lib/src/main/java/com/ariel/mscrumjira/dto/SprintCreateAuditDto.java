package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

public record SprintCreateAuditDto(		
		 Integer sprintKey, 	
		
		 Integer projectKey,	   	    
					
		 String teamKey,	
		
		 LocalDateTime startDate,
		
		 LocalDateTime endDate			
		
		 
		 )
{
}
