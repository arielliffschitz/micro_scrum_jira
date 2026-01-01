package com.ariel.scrumjira.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ariel.scrumjira.dto.UserCreateDto;
import com.ariel.scrumjira.dto.UserDto;
import com.ariel.scrumjira.dto.UserUpdateDto;
import com.ariel.scrumjira.entity.User;

public interface UserService {
    
    List<UserDto> findAll();
    
    Optional<UserDto> findByUsername(String username);

    UserDto save(UserCreateDto userCreateDto);

    UserDto update(UserUpdateDto userUpdateDto,String username);

    void deleteByUsername(String username);
}
