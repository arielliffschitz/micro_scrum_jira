package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

public  class SprintBacklogItemMapper {

    private SprintBacklogItemMapper() {}
    
    public  static SprintBacklogItem mapToDao( SprintBacklogItemDto dto) {
            return new SprintBacklogItem( 
                    dto.getTaskNumber(),             
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getPriority(),
                    dto.getEstimate(),
                    dto.getTaskState()               
            );
    }   

    public static SprintBacklogItem mapToDaoUpdate( SprintBacklogItemDto dto) {
        SprintBacklogItem dao  = new SprintBacklogItem();
                        dao.setTaskNumber(dto.getTaskNumber());            
                        dao.setTitle( dto.getTitle());
                        dao.setDescription(dto.getDescription());
                        dao.setPriority( dto.getPriority());
                        dao.setEstimate( dto.getEstimate());
                        dao.setTaskState(dto.getTaskState());
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
                    dao.getCreatedAt()
            );
        }  
}
