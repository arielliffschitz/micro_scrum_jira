package com.ariel.scrumjira.entity;

import java.util.UUID;

import com.ariel.mscrumjira.domain.entity.BaseEntity;
import com.ariel.mscrumjira.domain.enums.RoleName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "role")
public class Role extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {
    }

    public Role( RoleName name) {
		this.name = name;
	}

	public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    
}
