package com.ariel.scrumjira.service;

import java.util.List;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.RoleDto;
import com.ariel.scrumjira.dto.RoleCreateDto;

public interface RoleService {
	
	List<RoleDto> findAll();
	
	RoleDto save(RoleCreateDto role);
	
	void deleteByName(RoleName name);

}
