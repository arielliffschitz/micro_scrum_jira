package com.ariel.scrumjira.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User extends BaseEntity{ 

	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank
    @Column(unique = true)
    @Email
    private String username;

    @NotBlank
    private String password;

    @Column(name = "display_name")
    private String displayName;        
    
    private Boolean active;    

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", 
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = {@JoinColumn(name = "id_role") },
            uniqueConstraints = {@UniqueConstraint(columnNames = { "id_user", "id_role" }) })
    private Set<Role> roles;      

    public User( String username, String password, String displayName, Boolean active) {	
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.active = active;
	}

	public User() {
    }        

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }    

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    
}
