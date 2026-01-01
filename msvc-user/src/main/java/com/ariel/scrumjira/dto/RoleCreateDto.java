package com.ariel.scrumjira.dto;

import com.ariel.mscrumjira.domain.enums.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record RoleCreateDto (
	
	@NotNull
	@Enumerated(EnumType.STRING)
    RoleName name
	) {}