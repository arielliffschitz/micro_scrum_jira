package com.ariel.scrumjira.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.scrumjira.entity.Role;

public interface RoleRepository extends CrudRepository<Role, UUID>{
     Optional<Role> findByName(RoleName name);
     List<Role> findByNameIn(Set<RoleName> roleNames);
}
