package com.ariel.scrumjira.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.UserLoginDto;
import com.ariel.mscrumjira.service.PersistenceMetadataUtil;
import com.ariel.scrumjira.dto.UserCreateDto;
import com.ariel.scrumjira.dto.UserDto;
import com.ariel.scrumjira.dto.UserUpdateDto;
import com.ariel.scrumjira.entity.Role;
import com.ariel.scrumjira.entity.User;
import com.ariel.scrumjira.mapper.UserMapper;
import com.ariel.scrumjira.repository.RoleRepository;
import com.ariel.scrumjira.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService{

	private  UserRepository userRepository;
	private  RoleRepository roleRepository;  
	private  PasswordEncoder passwordEncoder; 		

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		return userRepository.findAll().stream()				
							 .map(UserMapper::fromUsertoUserDto)
							 .collect(Collectors.toList());       
	}  	

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.map(UserMapper::fromUsertoUserDto);
	}   

	@Override
	@Transactional
	public UserLoginDto findForLoginByUsername(String username) {
		User user = tryTofindByUsername(username);
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
		return UserMapper.fromUsertoUserLoginDto(user);
	}

	private User tryTofindByUsername(String username) {		
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"User with username: " +username+" not found"));
	}

	@Override
	@Transactional
	public UserDto create(UserCreateDto userCreateDto, String token) {
		User dao = createDao(userCreateDto);       		  
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		
		return UserMapper.fromUsertoUserDto(userRepository.save(dao));
	} 	

	@Override
	@Transactional
	public UserDto update( String username, UserUpdateDto userUpdateDto, String token) {
		User dao = userRepository.findByUsername(username).orElseThrow(); 
		dao = UserMapper.applyUpdateToUser(dao, userUpdateDto);
		if (userUpdateDto.getRoles()!=null)
			dao.setRoles(assignRoles(userUpdateDto.getRoles()));   	    	
		PersistenceMetadataUtil.BaseEntityUpdateFields(dao, token);
		return UserMapper.fromUsertoUserDto(userRepository.save(dao));    	        
	}

	@Override
	@Transactional
	public void deleteByUsername(String username){
		userRepository.deleteByUsername(username);
	}

	private User createDao(UserCreateDto dto) {
		return new User(
						 dto.getUsername(),
						 passwordEncoder.encode(dto.getPassword()),
						 dto.getDisplayName(),						 
						 assignRoles(dto.getRoles())
		);		
	}
	
	private Set<Role> assignRoles(Set<RoleName> roleNames) {        
		List<Role> rolesFromDb = roleRepository.findByNameIn(roleNames)
											   .stream()				                                                                         
											   .collect(Collectors.toList());                            
		return new HashSet<>(rolesFromDb);
	}				

}
