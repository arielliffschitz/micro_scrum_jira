package com.ariel.scrumjira.mapper;

import com.ariel.scrumjira.dto.RoleCreateDto;
import com.ariel.scrumjira.dto.RoleDto;
import com.ariel.scrumjira.entity.Role;

public class RoleMapper {
	
	public static RoleDto fromRoleToRoleDto(Role role) {
		return new RoleDto( role.getName(),
							role.getCreatedBy(),
							role.getCreatedAt());
	}

	public static Role fromRoleCreateDtoToRole(RoleCreateDto roleCreateDto) {
		return new Role(roleCreateDto.name());
	}

}
