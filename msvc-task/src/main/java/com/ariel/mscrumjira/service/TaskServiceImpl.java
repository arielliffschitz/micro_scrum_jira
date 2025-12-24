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
import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.TaskDto;

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
                .map(this::mapSprintToTask)
                .collect(Collectors.toList()); 
        List<TaskDto> taskSprintDtoList = StreamSupport.stream(clientProduct.findAll()
                .spliterator(),false)
                .map(this::mapProductToTask)
                .collect(Collectors.toList()); 

        List<TaskDto> taskDtoList= Stream.concat(taskProductDtoList.stream(), taskSprintDtoList.stream()).toList();

        return  taskDtoList.stream().sorted(Comparator.comparing(TaskDto::taskNumber).reversed()).toList();                                   
    }    

    @Override
    public Optional<TaskDto> findByTaskNumber(Integer taskNumber) {
        try {
             SprintBacklogItemDto  sprintDto = clientSprint.findByTaskNumber(taskNumber);
             return Optional.of(mapSprintToTask(sprintDto));
        } catch (FeignException.NotFound e) {
            return Optional.ofNullable(mapProductToTask(clientProduct.findByTaskNumber(taskNumber)));
        }      
    }    

    @Override
    public SprintBacklogItemDto moveFromProductToSprint(Integer taskNumber) {
        ProductBacklogItemDto productDto = clientProduct.findByTaskNumber(taskNumber);
        SprintBacklogItemDto  SprintDto  = clientSprint.save(mapFromProductDtoToSprintDto(productDto));
        clientProduct.deleteProductByTaskNumber(taskNumber);
        return SprintDto;
    }

    @Override
    public ProductBacklogItemDto moveFromSprintToProduct(Integer taskNumber) {
        SprintBacklogItemDto  SprintDto  = clientSprint.findByTaskNumber(taskNumber);
        ProductBacklogItemDto productDto = clientProduct.save(mapFromSprintDtoToProductDto(SprintDto));
        clientSprint.deleteProductByTaskNumber(taskNumber);
        return productDto;
    }
    private TaskDto mapSprintToTask(SprintBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),
                            dto.getTaskState(),
                            dto.getStartDate(),
                            dto.getEndDate(),
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            Boolean.TRUE          
                        );
    }
    private TaskDto mapProductToTask(ProductBacklogItemDto dto) {
        return new TaskDto( dto.getTaskNumber(), 
                            dto.getTitle(),
                            dto.getDescription(),
                            dto.getPriority(),
                            dto.getEstimate(),
                            null,
                            null,
                            null,
                            dto.getCreatedBy(),
                            dto.getCreatedAt(),
                            Boolean.FALSE          
                        );
    }
    private ProductBacklogItemDto mapFromSprintDtoToProductDto(SprintBacklogItemDto sprintDto) {
        return new ProductBacklogItemDto(                
                sprintDto.getTitle(),
                sprintDto.getDescription(),
                sprintDto.getPriority(),
                sprintDto.getEstimate(),              
                sprintDto.getCreatedBy(),
                sprintDto.getCreatedAt(),
                sprintDto.getTaskNumber()
        );
    }


    private SprintBacklogItemDto mapFromProductDtoToSprintDto(ProductBacklogItemDto productDto) {
        return new SprintBacklogItemDto(
                productDto.getTaskNumber(),
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getPriority(),
                productDto.getEstimate(),              
                productDto.getCreatedBy(),
                productDto.getCreatedAt()
                
        );
    }   
}
