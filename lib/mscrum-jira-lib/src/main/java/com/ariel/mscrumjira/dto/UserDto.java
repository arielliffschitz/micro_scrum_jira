package com.ariel.mscrumjira.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.ariel.mscrumjira.domain.enums.RoleName;

public class UserDto {    
   
    private String username;           
  
    private String displayName;      
   
    private Boolean active;    
   
    private LocalDateTime lastLogin;
  
    private Set<RoleName> roles;

    private String  createdBy;
    
    private  LocalDateTime createdAt;    

    public UserDto() {
    }
    public UserDto(String username, String displayName, Boolean active, LocalDateTime lastLogin, Set<RoleName> roles,
            String createdBy,  LocalDateTime createdAt ) {
        this.username = username;
        this.displayName = displayName;
        this.active = active;
        this.lastLogin = lastLogin;
        this.roles = roles;        
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }    

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Set<RoleName> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
    
    

    

}
