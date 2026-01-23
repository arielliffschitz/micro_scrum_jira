package com.ariel.scrumjira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.RoleDto;
import com.ariel.mscrumjira.service.AuditUtil;
import com.ariel.scrumjira.dto.RoleCreateDto;
import com.ariel.scrumjira.entity.Role;
import com.ariel.scrumjira.mapper.RoleMapper;
import com.ariel.scrumjira.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	RoleRepository repository;	
	
	public RoleServiceImpl(RoleRepository repository) {		
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleDto> findAll() {		
		return   repository.findAll()
						   .stream()
						   .map(RoleMapper::fromRoleToRoleDto)
						   .collect(Collectors.toList());  
	}

	@Override
	@Transactional
	public RoleDto save(RoleCreateDto roleCreateDto, String token) {
		Role dao = new Role(roleCreateDto.name());
		
		AuditUtil.BaseEntityCreatedFields(dao, token);
		return RoleMapper.fromRoleToRoleDto(repository
							.save(dao));
	}

	@Override
	@Transactional
	public void deleteByName(RoleName name) {
		repository.deleteByName(name);
	}

}
