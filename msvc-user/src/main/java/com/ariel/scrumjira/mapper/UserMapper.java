package com.ariel.scrumjira.mapper;

import java.util.HashSet;
import java.util.Set;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.UserDto;
import com.ariel.scrumjira.dto.UserCreateDto;

import com.ariel.scrumjira.dto.UserUpdateDto;
import com.ariel.scrumjira.entity.Role;
import com.ariel.scrumjira.entity.User;

public class UserMapper {

    public static UserDto fromUsertoUserDto(User user){         
        return new UserDto(
                    user.getUsername(),
                    user.getDisplayName(),
                    user.getActive(),
                    user.getLastLogin(),
                    getRolesName(user.getRoles()),
                    user.getCreatedBy(),
                    user.getCreatedAt()
        );
    }

    private static Set<RoleName> getRolesName(Set<Role> roles) {
         Set<RoleName> nameRoles = new HashSet<>(); 
         for (Role role:roles)
             nameRoles.add(role.getName());
         return nameRoles;
    }

    public static User fromCreateDtoToUser(UserCreateDto userCreateDto) {
        return new User(
        		userCreateDto.getUsername(),
        		userCreateDto.getPassword(),
        		userCreateDto.getDisplayName(),
        		true
        		);        		
    }   
    
    public static  User  applyUpdateToUser (  User currentUser, UserUpdateDto userUpdate ) {
    	
        		if (userUpdate.getPassword()!=null) currentUser.setPassword(userUpdate.getPassword());
        		if (userUpdate.getDisplayName()!=null) currentUser.setDisplayName(userUpdate.getDisplayName());
        		if (userUpdate.getActive()!=null) currentUser.setActive(userUpdate.getActive());        		
        		
        		return currentUser;      
    }
    
}
