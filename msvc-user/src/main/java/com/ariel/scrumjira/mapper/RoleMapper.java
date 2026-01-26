package com.ariel.scrumjira.mapper;

import com.ariel.mscrumjira.dto.RoleDto;


import com.ariel.scrumjira.entity.Role;

public class RoleMapper {
	
	public static RoleDto fromRoleToRoleDto(Role role) {
		return new RoleDto( role.getName(),
							role.getCreatedBy(),
							role.getCreatedAt());
	}

	

}
