package com.ariel.scrumjira.service;

import java.util.List;
import java.util.Optional;

import com.ariel.mscrumjira.dto.UserDto;
import com.ariel.mscrumjira.dto.UserLoginDto;
import com.ariel.scrumjira.dto.UserCreateDto;
import com.ariel.scrumjira.dto.UserUpdateDto;

public interface UserService {
    
    List<UserDto> findAll();
    
    Optional<UserDto> findByUsername(String username);

    UserDto create(UserCreateDto userCreateDto, String token);

    UserDto update(String username,UserUpdateDto userUpdateDto, String token);

    void deleteByUsername(String username);       

	Optional<UserLoginDto> findForLoginByUsername(String username);
}
