package com.ariel.scrumjira.dto;

import java.util.Set;

import com.ariel.mscrumjira.domain.enums.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreateDto {

    @NotNull
    @Size(min=5, max=254)   
    private String username;
   
    @NotNull   
    @Size(min=5, max=24)
    private String password;

    @NotNull
    @Size(min=3, max=100)
    private String displayName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Set<RoleName> roles;

    public UserCreateDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    

    
}
