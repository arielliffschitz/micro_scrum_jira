package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.enums.AuditTaskState;

public record TaskMovementAuditDto(

		Integer taskNumber,

		AuditTaskState auditTaskState,

		String createdBy,

		LocalDateTime createdAt	
		) {
}
