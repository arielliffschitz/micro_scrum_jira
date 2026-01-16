package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateDto;

public  class SprintBacklogItemMapper {

    private SprintBacklogItemMapper() {}
    
    public  static SprintBacklogItem mapToDao( SprintBacklogItemDto dto) {
            return new SprintBacklogItem( 
                    dto.getTaskNumber(),             
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getPriority(),
                    dto.getEstimate(),
                    dto.getCreatedBy(),
                    dto.getCreatedAt(),
                    dto.getTaskState()               
            );
    }   

    public static SprintBacklogItem mapToDaoUpdate( SprintBacklogItemDto dto) {
        SprintBacklogItem dao = mapToDao( dto);  
                        dao.setId(dto.getId());                                  
                        dao.setStartDate(dto.getStartDate());
                        dao.setEndDate(dto.getEndDate());
                        dao.setCreatedBy(dto.getCreatedBy());
                        dao.setCreatedAt(dto.getCreatedAt());            
        return dao;
    }                      

    public static SprintBacklogItemDto mapToDto(SprintBacklogItem dao) {
            return new SprintBacklogItemDto(
                    dao.getTaskNumber(),
                    dao.getTitle(),
                    dao.getDescription(),
                    dao.getPriority(),
                    dao.getEstimate(),
                    dao.getTaskState(),
                    dao.getStartDate(),
                    dao.getEndDate(),
                    dao.getCreatedBy(),
                    dao.getCreatedAt(),
                    dao.getUpdatedBy(),
                    dao.getUpdatedAt()
            );
        }

    public static SprintBacklogItemDto mapToDtoUpdate(SprintBacklogItem dao) {
        SprintBacklogItemDto dto =mapToDto( dao);
        dto.setId(dao.getId()) ;
        return dto;
    } 
    public static void applyUpdateToSprint(SprintBacklogItem currentTask, UpdateDto taskUpdate) {
      
       if (taskUpdate.getTitle() !=null) 
                             currentTask.setTitle(taskUpdate.getTitle());
       if (taskUpdate.getDescription() !=null) 
                         currentTask.setDescription(taskUpdate.getDescription());
       if (taskUpdate.getPriority() !=null) 
                         currentTask.setPriority(taskUpdate.getPriority());
       if (taskUpdate.getEstimate() !=null) 
                         currentTask.setEstimate(taskUpdate.getEstimate());      
    } 
}
