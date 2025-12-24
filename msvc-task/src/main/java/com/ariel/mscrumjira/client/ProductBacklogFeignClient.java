package com.ariel.mscrumjira.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
@FeignClient(name = "msvc-product-backlog", path = "/product-backlog-items")
public interface ProductBacklogFeignClient {   

    @GetMapping("/task-number/{taskNumber}")
    ProductBacklogItemDto findByTaskNumber(@PathVariable Integer taskNumber);

    @DeleteMapping("/task-number/{taskNumber}")
    void deleteProductByTaskNumber(@PathVariable Integer taskNumber);
    
    @PostMapping
    ProductBacklogItemDto save(@RequestBody ProductBacklogItemDto dto);

    @GetMapping
    List<ProductBacklogItemDto> findAll();
}
