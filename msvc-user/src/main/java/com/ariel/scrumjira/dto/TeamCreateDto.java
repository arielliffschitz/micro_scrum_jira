package com.ariel.scrumjira.dto;

import jakarta.validation.constraints.NotNull;

public record TeamCreateDto(
		@NotNull
		String teamKey,
		@NotNull
		String username
		) {

	
}
