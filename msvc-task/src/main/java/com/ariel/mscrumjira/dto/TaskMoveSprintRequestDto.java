package com.ariel.mscrumjira.dto;

import jakarta.validation.constraints.NotNull;

public record TaskMoveSprintRequestDto(
		
		@NotNull
		Integer taskNumber,
		@NotNull
		Integer sprintKey
		) {		
}
