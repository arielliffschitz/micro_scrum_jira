package com.ariel.scrumjira.mapper;

import com.ariel.scrumjira.dto.TeamCreateDto;
import com.ariel.scrumjira.dto.TeamDto;
import com.ariel.scrumjira.entity.Team;

public class TeamMapper {	
	
	public static TeamDto mapToDto(Team dao) {		
		return new TeamDto(
				dao.getTeamKey(),
				dao.getUsername(),				
				dao.getCreatedBy(),
				dao.getCreatedAt(),
				dao.getUpdatedBy(),
				dao.getUpdatedAt()
				);
	}
//	public static Team mapFromCreateDtoToDao(TeamCreateDto dto) {	
//		return new Team(
//				dto.teamKey(),
//				dto.username()
//				); 
//}

}
