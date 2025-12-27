package com.ariel.mscrumjira.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import com.ariel.mscrumjira.client.ProductBacklogFeignClient;
import com.ariel.mscrumjira.client.SprintBacklogFeignClient;
import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.ProductCreateDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;
import com.ariel.mscrumjira.mapper.TaskItemMapper;

import feign.FeignException;
@Service
public class TaskServiceImpl implements TaskService{    
    
    private final ProductBacklogFeignClient clientProduct;
    private final SprintBacklogFeignClient  clientSprint;        


    public TaskServiceImpl(ProductBacklogFeignClient clientProduct, SprintBacklogFeignClient clientSprint) {
        this.clientProduct = clientProduct;
        this.clientSprint = clientSprint;
    }

    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskProductDtoList = StreamSupport.stream(clientSprint.findAll()
                .spliterator(),false)
                .map(TaskItemMapper::mapSprintToTask)
                .collect(Collectors.toList()); 
        List<TaskDto> taskSprintDtoList = StreamSupport.stream(clientProduct.findAll()
                .spliterator(),false)
                .map(TaskItemMapper::mapProductToTask)
                .collect(Collectors.toList()); 

        List<TaskDto> taskDtoList= Stream.concat(taskProductDtoList.stream(), taskSprintDtoList.stream()).toList();

        return  taskDtoList.stream().sorted(Comparator.comparing(TaskDto::taskNumber).reversed()).toList();                                   
    }    

    @Override
    public Optional<TaskDto> findByTaskNumber(Integer taskNumber) {
        try {
             SprintBacklogItemDto  sprintDto = clientSprint.findByTaskNumber(taskNumber);
             return Optional.of(TaskItemMapper.mapSprintToTask(sprintDto));
        } catch (FeignException.NotFound e) {
            return Optional.ofNullable(TaskItemMapper.mapProductToTask(clientProduct.findByTaskNumber(taskNumber)));
        }      
    }    
    @Override
        public TaskDto create(ProductCreateDto dto) {
           return TaskItemMapper.mapProductToTask(clientProduct.save(TaskItemMapper.mapCreateToProductDto(dto)));
    }
    
    @Override
    public TaskDto moveFromProductToSprint(Integer taskNumber) {
        ProductBacklogItemDto productDto = clientProduct.findByTaskNumber(taskNumber);
        SprintBacklogItemDto  sprintDto  = clientSprint.save(TaskItemMapper.mapFromProductDtoToSprintDto(productDto));
        clientProduct.deleteProductByTaskNumber(taskNumber);
        return TaskItemMapper.mapSprintToTask(sprintDto);
    }

    @Override
    public TaskDto moveFromSprintToProduct(Integer taskNumber) {
        SprintBacklogItemDto  SprintDto  = clientSprint.findByTaskNumber(taskNumber);
        ProductBacklogItemDto productDto = clientProduct.save(TaskItemMapper.mapFromSprintDtoToProductDto(SprintDto));
        clientSprint.deleteProductByTaskNumber(taskNumber);
        return TaskItemMapper.mapProductToTask(productDto);
    }

    @Override
    public Optional<TaskDto> update(TaskDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public TaskDto updateState(Integer taskNumber, TaskState taskState) {
         return TaskItemMapper.mapSprintToTask(clientSprint.updateState(taskNumber, taskState));
    }

    
    
     /* @Override
    @Transactional
    public ProductBacklogItemDto update(ProductBacklogItemDto dto) {
        Optional<ProductBacklogItem> itemOptional = repository.findByTaskNumber(taskNumber);
        ProductBacklogItem  item = itemOptional.orElseThrow();

        item.setTitle(dto.getTitle());
        if (StringUtils.hasText(dto.getDescription())) 
             item.setDescription(dto.getDescription());
        item.setPriority(dto.getPriority());
        item.setEstimate(dto.getEstimate());   

        repository.save(item);
        return mapToDto(item);
    } */
}
