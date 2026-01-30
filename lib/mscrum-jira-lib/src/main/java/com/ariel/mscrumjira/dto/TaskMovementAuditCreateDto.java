package com.ariel.mscrumjira.dto;

import com.ariel.mscrumjira.domain.enums.AuditTaskState;

public record TaskMovementAuditCreateDto(
		
		Integer taskNumber,

		AuditTaskState auditTaskState
		
		) {
}
