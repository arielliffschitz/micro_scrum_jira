package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.ProjectCreateDto;
import com.ariel.mscrumjira.dto.ProjectDto;
import com.ariel.mscrumjira.dto.ProjectUpdateDto;
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
	public static Project mapToCreateDao(ProjectCreateDto dto){
        return  new Project(                                               	        		
	        		dto.name(),
	        		dto.description()	        			        		               
                );
    }
	
	public static void applyUpdateToProject(Project currentProject, ProjectUpdateDto projectUpdateDto) {
		if (projectUpdateDto.getName() !=null) 
			currentProject.setName(projectUpdateDto.getName());
		if (projectUpdateDto.getDescription() !=null) 
			currentProject.setDescription(projectUpdateDto.getDescription());
		if (projectUpdateDto.getState() !=null) 
			currentProject.setState(projectUpdateDto.getState());			
	}
	public static Project mapToDao(ProjectDto dto) {
		 return  new Project(                                               	        		
	        		dto.getName(),
	        		dto.getDescription()	        			        		               
             );
	}
}
