package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.enums.RoleName;

public record RoleDto (	
						RoleName name,	
						String  createdBy,    
					    LocalDateTime createdAt
						) {}


