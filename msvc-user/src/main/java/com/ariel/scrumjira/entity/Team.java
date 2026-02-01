package com.ariel.scrumjira.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ariel.mscrumjira.domain.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "team", uniqueConstraints = {@UniqueConstraint(columnNames = {"team_key", "username"})})
public class Team extends BaseEntity{
	
	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;
	
	@NotNull
	@Column(name = "team_key", nullable = false)
    private String teamKey;
	
	@NotNull
	@Column(nullable = false)	
	private String username;	
	
	private Boolean active;

	public Team() {		
	}
	
	public Team( String teamKey, String username, Boolean active) {		
		this.teamKey = teamKey;
		this.username = username;
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	

	public String getTeamKey() {
		return teamKey;
	}

	public UUID getId() {
		return id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
		
}
