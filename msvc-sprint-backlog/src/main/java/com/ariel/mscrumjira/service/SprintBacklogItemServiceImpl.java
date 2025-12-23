package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {
   
    private final SprintBacklogItemRepository repository;

    public SprintBacklogItemServiceImpl( SprintBacklogItemRepository repository) {       
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintBacklogItemDto> findAll() {
        return StreamSupport.stream(repository.findAll()
                .spliterator(),false)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }  
     
    @Override
    @Transactional
    public SprintBacklogItemDto save(SprintBacklogItemDto dto) {
        if (dto.getTaskState()==null)dto.setTaskState(TaskState.PENDING);        
        SprintBacklogItem dao =repository.save(mapToDao(dto));
        return mapToDto(dao);        
    }

    @Override
    @Transactional
    public Optional<SprintBacklogItemDto> updateState(Integer taskNumber, TaskState taskState) {
        return repository.findByTaskNumber(taskNumber)
                .map(dao -> {
                    dao = actualizeTaskStateAndDate(taskState, dao);
                    return mapToDto(repository.save(dao));
                });
    }  

    @Override
    @Transactional(readOnly = true)
    public Optional<SprintBacklogItemDto> findByTaskNumber(Integer taskNumber) {
        Optional<SprintBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);
        return itemOptional.map(this::mapToDto);
    }          

    @Override
    @Transactional
    public void deleteByTaskNumber(Integer taskNumber) {
        repository.deleteByTaskNumber(taskNumber);
    }      

   private SprintBacklogItem mapToDao( SprintBacklogItemDto dto) {
        return new SprintBacklogItem( 
                dto.getTaskNumber(),             
                dto.getTitle(),
                dto.getDescription(),
                dto.getPriority(),
                dto.getEstimate(),
                dto.getTaskState()               
        );
    }          

    private SprintBacklogItemDto mapToDto(SprintBacklogItem dao) {
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

    private SprintBacklogItem actualizeTaskStateAndDate(TaskState taskState, SprintBacklogItem sprintBacklogItem) {
        if (sprintBacklogItem.getTaskState() == TaskState.PENDING) {
            if (taskState == TaskState.IN_PROGRESS) {
                sprintBacklogItem.setTaskState(taskState);
                sprintBacklogItem.setStartDate(LocalDateTime.now());
            } else if (taskState == TaskState.DONE) {
                sprintBacklogItem.setTaskState(taskState);
                sprintBacklogItem.setEndDate(LocalDateTime.now());
            }
        } else if (sprintBacklogItem.getTaskState() == TaskState.IN_PROGRESS && taskState == TaskState.DONE) {
            sprintBacklogItem.setTaskState(taskState);
            sprintBacklogItem.setEndDate(LocalDateTime.now());
        }
        return sprintBacklogItem;
    }       
}
