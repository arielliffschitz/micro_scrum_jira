package com.ariel.mscrumjira.dto;	
	

import java.time.LocalDateTime;
import java.util.Set;

public class UserLoginDto {	 	   
	   
	    
	    private String username;

	   
	    private String password;	    	       
	    
	    private Boolean active; 	   	   

	    
	    private Set<String> roles;   
	    
	    private LocalDateTime lastLogin;

	    public UserLoginDto( String username, String password,  Boolean active, Set<String> roles, LocalDateTime lastLogin) {	
			this.username = username;
			this.password = password;			
			this.active = active;
			this.roles = roles;
			this.lastLogin = lastLogin;
		}

		public UserLoginDto() {
	    }       	   

	    public LocalDateTime getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(LocalDateTime lastLogin) {
			this.lastLogin = lastLogin;
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

	    public Boolean getActive() {
	        return active;
	    }

	    public void setActive(Boolean active) {
	        this.active = active;
	    }
	   
	    public Set<String> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<String> roles) {
	        this.roles = roles;
	    }

	    
}



