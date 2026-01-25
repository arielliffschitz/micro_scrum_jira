package com.ariel.mscrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;


public interface SprintBacklogItemRepository extends JpaRepository<SprintBacklogItem, UUID> {

    Optional<SprintBacklogItem>  findByTaskNumber(Integer taskNumber);

    void deleteByTaskNumber(Integer taskNumber);

}
