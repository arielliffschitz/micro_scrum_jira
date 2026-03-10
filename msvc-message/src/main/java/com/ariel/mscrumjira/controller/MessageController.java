package com.ariel.mscrumjira.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.service.*;

import jakarta.validation.*;

@RestController
public class MessageController {

	private final Logger logger = LoggerFactory.getLogger(MessageController.class);

	private MessageService service;

	public MessageController(MessageService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<MessageListDto>> findByReceiverAndReadFlag(
			@RequestParam(defaultValue = "false") boolean readFlag, @RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(service.findByReceiverAndReadFlag(token, readFlag));
	}

	@GetMapping("/message-count")
	public Integer getUnreadMessagesCount(@RequestParam String username) {
		return service.getUnreadMessagesCount(username);
	}

	@GetMapping("/message-key/{messageKey}")
	public ResponseEntity<MessageDto> findByMessageKey(@PathVariable Integer messageKey,
			@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(service.findByMessageKey(messageKey));
	}

	@PostMapping
	public ResponseEntity<MessageDto> create(@RequestBody @Valid MessageCreateDto createDto,
			@RequestHeader("Authorization") String token) {
		logger.info("creating Message to: {} ", createDto.receiver());
		UUID id = service.create(createDto, token);

		return ResponseEntity.ok(service.findById(id));
	}

	@PutMapping("/message-key/{messageKey}")
	public void toogleReadFlag(@PathVariable Integer messageKey) {
		service.toggleRead(messageKey);
	}

	@DeleteMapping("/message-key/{messageKey}")
	public void deleteByMessageKey(@PathVariable Integer messageKey) {
		service.deleteByMessageKey(messageKey);
	}
}
