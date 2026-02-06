package com.ariel.mscrumjira.service;

import java.time.LocalDateTime;

import com.ariel.mscrumjira.domain.entity.BaseEntity;
import com.auth0.jwt.JWT;


public class AuditUtil  {

	public static void BaseEntityCreatedFields(BaseEntity entity, String token) {			     	       	        
		
		entity.setCreatedBy(decodeToUsername(token));
		entity.setCreatedAt(LocalDateTime.now());				
	}

	public static void BaseEntityUpdateFields(BaseEntity entity, String token) {
		
		entity.setUpdatedBy(decodeToUsername(token));
		entity.setUpdatedAt(LocalDateTime.now());		
	}
	
	private static String decodeToUsername(String token) {
		return JWT.decode(token.substring(7)).getSubject();
	}
}
