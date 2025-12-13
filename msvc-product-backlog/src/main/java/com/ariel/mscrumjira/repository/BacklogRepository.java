package com.ariel.mscrumjira.repository;

import org.springframework.data.repository.CrudRepository;

import com.ariel.mscrumjira.entity.BacklogItem;

public interface BacklogRepository extends CrudRepository<BacklogItem, Long>{

}
