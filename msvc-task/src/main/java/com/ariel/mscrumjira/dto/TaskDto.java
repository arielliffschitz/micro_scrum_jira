package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.enums.TaskState;

public record TaskDto(  Integer taskNumber, 
                        String title,
                        String description,
                        Integer priority,
                        Integer estimate,
                        TaskState taskState,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        String createdBy,
                        LocalDateTime createdAt,
                        String updatedBy,
                        LocalDateTime updateAt,
                        Boolean sprint
                    ) {

}
