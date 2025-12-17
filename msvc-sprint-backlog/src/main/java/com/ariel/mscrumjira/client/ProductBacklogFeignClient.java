package com.ariel.mscrumjira.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ariel.mscrumjira.dto.ProductBacklogItemDto;

@FeignClient(name = "msvc-product-backlog", url = "http://localhost:8081/product-backlog-items")
public interface ProductBacklogFeignClient {

    @GetMapping("/{id}")
    ProductBacklogItemDto findProductById(@PathVariable UUID id);

}
