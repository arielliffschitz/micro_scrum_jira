package com.ariel.scrumjira.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.scrumjira.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{
     Optional<User> findByUsername(String username);
     
     void deleteByUsername(String username);
}
