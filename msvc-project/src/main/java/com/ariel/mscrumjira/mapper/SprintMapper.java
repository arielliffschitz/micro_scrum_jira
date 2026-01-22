package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.entity.Sprint;

public class SprintMapper {

	public static SprintDto mapToDto (Sprint dao) {

		return  new SprintDto(
				dao.getSprintKey(),
				dao.getProject(),
				dao.getTeamKey(),
				dao.getState(),
				dao.getStartDate(),
				dao.getEndDate(),
				dao.getCreatedBy(),
				dao.getCreatedAt(),
				dao.getUpdatedBy(),
				dao.getUpdatedAt() 
				);

	}

}
