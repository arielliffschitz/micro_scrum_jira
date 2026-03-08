package com.ariel.mscrumjira.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;
import com.ariel.mscrumjira.mapper.*;
import com.ariel.mscrumjira.repository.*;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageRepository repository;
	private UserFeignClient userClient;
	
	
	public MessageServiceImpl(MessageRepository repository, UserFeignClient userClient) {		
		this.repository = repository;
		this.userClient = userClient;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MessageDto> findByReceiverAndReadFlag(String token, boolean readFlag) {
		String receiver = PersistenceMetadataUtil.decodeToUsername(token);
		return repository.findByReceiverAndReadFlag(receiver, readFlag).stream()				                
				.map(MessageMapper::mapToDto)
				 .collect(Collectors.toList()); 
	}

	@Override
	@Transactional(readOnly = true)
	public List<MessageDto> findByCreatedBy(String createdBy) {
		return repository.findByCreatedBy(createdBy).stream()				                
				.map(MessageMapper::mapToDto)
				 .collect(Collectors.toList()); 
	}

	@Override
	@Transactional(readOnly = true)
	public MessageDto findById(UUID id) {		
		return MessageMapper.mapToDto(repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Save Message not found")));
	}
	
	@Override
	@Transactional
	public UUID create(MessageCreateDto createDto, String token) {
		validateCreate(createDto.receiver());
		Message dao = MessageMapper.mapFromCreateToDao(createDto);
		PersistenceMetadataUtil.BaseEntityCreatedFields(dao, token);
		
		return repository.save(dao).getId();
	}	
		
	@Override
	@Transactional 
	public void toggleRead(Integer messageKey, boolean readFlag) {
		Message dao = repository.findByMessageKey(messageKey)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
		dao.setReadFlag(readFlag);
		repository.save(dao);
	}
	
	private void validateCreate( String receiver) {		
		 if ( ! userClient.existByUsername(receiver)) 
			 throw new IllegalArgumentException("The username: "+receiver+ " doesn't exist");		
	}	
}
