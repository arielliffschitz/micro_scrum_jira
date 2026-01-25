package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.domain.entity.ProductBacklogItem;



public interface ProductBacklogRepository extends JpaRepository<ProductBacklogItem, UUID>{

    Optional<ProductBacklogItem> findByTaskNumber(Integer taskNumber);

    void deleteByTaskNumber(Integer taskNumber);
}
