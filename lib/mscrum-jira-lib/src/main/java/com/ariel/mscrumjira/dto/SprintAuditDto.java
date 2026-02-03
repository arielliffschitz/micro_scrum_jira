package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

public record SprintAuditDto (
	
	 Integer sprintKey, 	
	
	 Integer projectKey,	   	    
				
	 String teamKey,	
	
	 LocalDateTime startDate,
	
	 LocalDateTime endDate,
	
	 String archivedBy,
	
	 LocalDateTime archivedAt	
	
	 )
{
}
