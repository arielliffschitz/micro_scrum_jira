package com.ariel.scrumjira.service;

import java.util.List;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.RoleDto;

public interface RoleService {
	
	List<RoleDto> findAll();
	
	RoleDto save(RoleName name, String token);
	
	void deleteByName(RoleName name);

}
