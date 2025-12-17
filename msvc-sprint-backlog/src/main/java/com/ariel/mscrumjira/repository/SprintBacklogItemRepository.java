package com.ariel.mscrumjira.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ariel.mscrumjira.domain.entity.SprintBacklogItem;

public interface SprintBacklogItemRepository extends CrudRepository<SprintBacklogItem, UUID> {

}
