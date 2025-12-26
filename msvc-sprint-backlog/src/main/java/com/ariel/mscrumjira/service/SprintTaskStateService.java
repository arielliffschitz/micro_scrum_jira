package com.ariel.mscrumjira.service;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

public interface SprintTaskStateService {

    SprintBacklogItemDto applyTransition(TaskState next, SprintBacklogItemDto dto) ;   
}
