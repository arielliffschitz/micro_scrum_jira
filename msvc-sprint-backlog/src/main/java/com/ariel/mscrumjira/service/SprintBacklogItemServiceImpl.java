package com.ariel.mscrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateDto;
import com.ariel.mscrumjira.mapper.SprintBacklogItemMapper;
import com.ariel.mscrumjira.repository.SprintBacklogItemRepository;

@Service
public class SprintBacklogItemServiceImpl implements SprintBacklogItemService {
   
    private final SprintBacklogItemRepository repository;
    private final SprintTaskStateService stateService;    

    public SprintBacklogItemServiceImpl(SprintBacklogItemRepository repository, SprintTaskStateService stateService) {
        this.repository = repository;
        this.stateService = stateService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintBacklogItemDto> findAll() {
        return StreamSupport.stream(repository.findAll()
                .spliterator(),false)
                .map(SprintBacklogItemMapper::mapToDto)
                .collect(Collectors.toList());
    }  
     
    @Override
    @Transactional(readOnly = true)
    public Optional<SprintBacklogItemDto> findByTaskNumber(Integer taskNumber) {
        Optional<SprintBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);
        return itemOptional.map(SprintBacklogItemMapper::mapToDto);
    }          
    @Override
    @Transactional
    public SprintBacklogItemDto save(SprintBacklogItemDto dto) {
        if (dto.getTaskState()==null) dto.setTaskState(TaskState.PENDING);        
        SprintBacklogItem dao =repository.save(SprintBacklogItemMapper.mapToDao(dto));
        return SprintBacklogItemMapper.mapToDto(dao);        
    }

    @Override
    @Transactional
    public Optional<SprintBacklogItemDto> updateState(Integer taskNumber, TaskState taskState) {

        return repository.findByTaskNumber(taskNumber)
                .map(dao -> {                    
                    dao = SprintBacklogItemMapper.mapToDaoUpdate(stateService.applyTransition(taskState, SprintBacklogItemMapper.mapToDtoUpdate(dao)));
                   
                    return SprintBacklogItemMapper.mapToDto(repository.save(dao));
                });
    }  

    @Override
    @Transactional
    public void deleteByTaskNumber(Integer taskNumber) {
        repository.deleteByTaskNumber(taskNumber);
    }

    @Override
    @Transactional
    public Optional<SprintBacklogItemDto> update(Integer taskNumber, UpdateDto taskUpdate) {
         return repository.findByTaskNumber(taskNumber)
                .map(dao -> {                    
                    dao = SprintBacklogItemMapper.applyUpdateToSprint   (dao, taskUpdate);
                    return SprintBacklogItemMapper.mapToDto(repository.save(dao));
                });
    }

      
 
}
