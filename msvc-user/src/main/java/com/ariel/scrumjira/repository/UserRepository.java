package com.ariel.scrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ariel.scrumjira.entity.User;

public interface UserRepository extends CrudRepository<User, UUID>{
     Optional<User> findByUsername(String username);
     
     void deleteByUsername(String username);
}
