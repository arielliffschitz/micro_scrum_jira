package com.ariel.scrumjira.dto;

import java.util.Set;

import com.ariel.mscrumjira.domain.enums.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;

public class UserUpdateDto {	
	    
	    @Size(min=5, max=24)
	    private String password;

	    @Size(min=3, max=100)
	    private String displayName;
	    
	    private Boolean active; 

	    @Enumerated(EnumType.STRING)
	    private Set<RoleName> roles;

		public UserUpdateDto() {			
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

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
		}

		public Set<RoleName> getRoles() {
			return roles;
		}

		public void setRoles(Set<RoleName> roles) {
			this.roles = roles;
		}	
	    
	    
	    
}
