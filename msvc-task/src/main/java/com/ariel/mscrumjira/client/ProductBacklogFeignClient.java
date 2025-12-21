package com.ariel.mscrumjira.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;
@FeignClient(name = "msvc-product-backlog", path = "/product-backlog-items")
public interface ProductBacklogFeignClient {

    @GetMapping("/{id}")
    ProductBacklogItemDto findProductById(@PathVariable UUID id);

    @GetMapping("/task-number/{taskNumber}")
    ProductBacklogItemDto findProductByTaskNumber(@PathVariable Integer taskNumber);

    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable UUID id);
    
    @PostMapping
    public ResponseEntity<ProductBacklogItemDto> create(@RequestBody ProductBacklogItemDto dto);

}
