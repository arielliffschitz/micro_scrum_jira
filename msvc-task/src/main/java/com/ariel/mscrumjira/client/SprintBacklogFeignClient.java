package com.ariel.mscrumjira.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ariel.mscrumjira.domain.enums.TaskState;
import com.ariel.mscrumjira.dto.SprintBacklogItemDto;
import com.ariel.mscrumjira.dto.UpdateDto;

@FeignClient(name = "msvc-sprint-backlog", path = "/sprint-backlog-items")
public interface SprintBacklogFeignClient {

    @PostMapping
    SprintBacklogItemDto save(@RequestBody SprintBacklogItemDto dto);

    @GetMapping("/task-number/{taskNumber}")
    SprintBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber);

    @DeleteMapping("/task-number/{taskNumber}")
    void deleteProductByTaskNumber(@PathVariable Integer taskNumber);

    @GetMapping
    List<SprintBacklogItemDto> findAll();

    @PutMapping("/{taskNumber}/state/{taskState}")
    public SprintBacklogItemDto updateState(@PathVariable Integer taskNumber,  @PathVariable TaskState taskState);
   
    @PutMapping("/task-number/{taskNumber}")
    SprintBacklogItemDto update(@PathVariable Integer taskNumber, @RequestBody UpdateDto taskUpdate);
} 

