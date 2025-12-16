package com.ariel.mscrumjira.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;

public interface ProductBacklogRepository extends CrudRepository<ProductBacklogItem, UUID>{

}
