package com.ariel.mscrumjira.service;

import java.util.*;

import com.ariel.mscrumjira.dto.*;

public interface MessageService {	
	
	List<MessageListDto>findByReceiverAndReadFlag(String token, boolean readFlag);
	
	List<MessageDto>findByCreatedBy(String createdBy);
	
	Integer getUnreadMessagesCount(String username);
	
	MessageDto findByMessageKey(Integer  messageKey);
	
	MessageDto findById(UUID id);
	
	UUID create(MessageCreateDto createDto, String token);
	
	void toggleRead( Integer messageKey);		
	
	void deleteByMessageKey(Integer messageKey);
	
}
