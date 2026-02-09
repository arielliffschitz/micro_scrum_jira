package com.ariel.mscrumjira.domain.enums;

import java.util.Set;

public enum TaskState {

	PENDING,
	IN_PROGRESS,
	BLOCKED,
	DONE,
	REVIEW,
	CANCELED,
	ON_HOLD,
	ARCHIVED;

	private Set<TaskState> validNext;

	static {
	    PENDING.validNext = Set.of(IN_PROGRESS, BLOCKED, ARCHIVED);
	    IN_PROGRESS.validNext = Set.of(DONE, BLOCKED, ARCHIVED);
	    BLOCKED.validNext = Set.of(IN_PROGRESS, ARCHIVED);
	    DONE.validNext = Set.of(ARCHIVED);
	    REVIEW.validNext = Set.of();
	    CANCELED.validNext = Set.of();
	    ON_HOLD.validNext = Set.of();
	    ARCHIVED.validNext = Set.of();
	}

	public boolean canTransitionTo(TaskState next) {
	    return validNext.contains(next);
	}

}
