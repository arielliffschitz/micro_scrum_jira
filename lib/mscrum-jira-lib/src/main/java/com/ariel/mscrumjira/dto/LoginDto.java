package com.ariel.mscrumjira.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDto(
		@NotNull
		@Size(min=5, max=254)
		String username,
		@NotNull
		@Size(min=5, max=24)
		String password
		) {

}
