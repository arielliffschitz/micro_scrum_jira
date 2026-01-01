package com.ariel.scrumjira.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.mscrumjira.domain.enums.RoleName;
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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;  
    
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;       
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll()
                                .spliterator(),false)
                                .map(UserMapper::fromUsertoUserDto)
                                .collect(Collectors.toList());       
    }

    @Override
    @Transactional
    public UserDto save(UserCreateDto userCreateDto) {
        User user = UserMapper.fromCreateDtoToUser(userCreateDto);  
        user.setRoles(assignRoles(userCreateDto.getRoles()));       
        return UserMapper.fromUsertoUserDto(userRepository.save(user));
    }   

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findByUsername(String username) {
          Optional<User> user = userRepository.findByUsername(username);
          return user.map(UserMapper::fromUsertoUserDto);
    }   

    @Override
    @Transactional
    public UserDto update(UserUpdateDto userUpdateDto, String username) {
    	User user = userRepository.findByUsername(username).orElseThrow(); 
    	user = UserMapper.applyUpdateToUser(user, userUpdateDto);
    	if (userUpdateDto.getRoles()!=null)
    			user.setRoles(assignRoles(userUpdateDto.getRoles()));   	    	
    	
    	return UserMapper.fromUsertoUserDto(userRepository.save(user));    	        
    }

    @Override
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
    
    private Set<Role> assignRoles(Set<RoleName> roleNames) {
        
        List<Role> rolesFromDb = StreamSupport.stream(roleRepository.findByNameIn(roleNames)
                                .spliterator(), false)                                                                          
                                .collect(Collectors.toList());                     
        
        return new HashSet<>(rolesFromDb);
    }    
}
