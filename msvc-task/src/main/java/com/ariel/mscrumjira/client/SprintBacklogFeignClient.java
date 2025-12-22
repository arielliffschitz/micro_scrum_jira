package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ariel.mscrumjira.dto.SprintBacklogItemDto;

@FeignClient(name = "msvc-sprint-backlog", path = "/sprint-backlog-items")
public interface SprintBacklogFeignClient {

    @PostMapping
    public  SprintBacklogItemDto save(@RequestBody SprintBacklogItemDto dto);
} 

