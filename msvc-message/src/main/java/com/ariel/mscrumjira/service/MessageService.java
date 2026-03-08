package com.ariel.mscrumjira.service;

import java.util.*;

import com.ariel.mscrumjira.dto.*;

public interface MessageService {	
	
	List<MessageDto>findByReceiverAndReadFlag(String token, boolean readFlag);
	
	List<MessageDto>findByCreatedBy(String createdBy);
	
	MessageDto findById(UUID id);
	
	UUID create(MessageCreateDto createDto, String token);
	
	void toggleRead( Integer messageKey, boolean readFlag);		
	
}
