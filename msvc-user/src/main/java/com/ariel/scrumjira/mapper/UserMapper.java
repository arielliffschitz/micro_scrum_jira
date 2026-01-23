package com.ariel.scrumjira.mapper;

import java.util.HashSet;
import java.util.Set;

import com.ariel.mscrumjira.domain.enums.RoleName;
import com.ariel.mscrumjira.dto.UserDto;
import com.ariel.mscrumjira.dto.UserLoginDto;
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
    
    public static  User  applyUpdateToUser (  User currentUser, UserUpdateDto userUpdate ) {
    	
        		if (userUpdate.getPassword()!=null) currentUser.setPassword(userUpdate.getPassword());
        		if (userUpdate.getDisplayName()!=null) currentUser.setDisplayName(userUpdate.getDisplayName());
        		if (userUpdate.getActive()!=null) currentUser.setActive(userUpdate.getActive());        		
        		
        		return currentUser;      
    }
    public static UserLoginDto fromUsertoUserLoginDto(User user) {
    	
    	return new UserLoginDto(
    			user.getUsername(),  
    			user.getPassword(),
    			user.getActive(),
    			getRolesNameLogin(user.getRoles()),
    			user.getLastLogin()
    			);
    	
    }
    
    private static Set<RoleName> getRolesName(Set<Role> roles) {
        Set<RoleName> nameRoles = new HashSet<>(); 
        for (Role role:roles)
            nameRoles.add(role.getName());
        return nameRoles;
   }
    
    private static Set<String> getRolesNameLogin(Set<Role> roles) {
        Set<String> nameRoles = new HashSet<>(); 
        for (Role role:roles)
            nameRoles.add( role.getName().name());
        return nameRoles;
   }
}
