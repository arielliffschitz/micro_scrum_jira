package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.ProjectCreateAuditDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;
import com.ariel.mscrumjira.dto.SprintAuditDto;
import com.ariel.mscrumjira.entity.Project;

public class ProjectMapper {

	public static ProjectDto mapToDto(Project dao){
        return  new ProjectDto(                                                 
		        		dao.getProjectKey(),
		        		dao.getName(),
		        		dao.getDescription(),
		        		dao.getState(),		        		
		        		dao.getCreatedBy(),
		        		dao.getCreatedAt(),
		        		dao.getUpdatedBy(),
		        		dao.getUpdatedAt()                    
                );
    }	
	
	public static void applyUpdateToProject(Project currentProject, ProjectUpdateDto projectUpdateDto) {
		if (projectUpdateDto.name() !=null) 
			currentProject.setName(projectUpdateDto.name());
		if (projectUpdateDto.description() !=null) 
			currentProject.setDescription(projectUpdateDto.description());	
		if (projectUpdateDto.state() !=null) 
			currentProject.setState(projectUpdateDto.state());
	}

	public static ProjectCreateAuditDto mapToProjectCreateAuditDto(Project dao) {
		 return  new ProjectCreateAuditDto(
				 dao.getProjectKey(),
				 dao.getName(),
				 dao.getDescription()
				 );
	}	
	
}
