package com.ariel.mscrumjira.entity;

import java.util.*;

import org.hibernate.annotations.*;

import com.ariel.mscrumjira.domain.entity.*;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message extends BaseCreateEntity{


	@Id
	@GeneratedValue
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")	
	private UUID id;
	
	@Column(name = "message_key", nullable = false, unique = true)
    private Integer messageKey;
	
	@Column( nullable = false)
	private String receiver;
	
	private String subject;
	
	private String content;
	
	@Column(name = "read_flag")
	private boolean readFlag;

	public Message() {		
	}
	
	
	public Message(String receiver, String subject, String content) {		
		this.receiver = receiver;
		this.subject = subject;
		this.content = content;
		this.readFlag = false;
	}


	public Integer getMessageKey() {
		return messageKey;
	}


	public void setMessageKey(Integer messageKey) {
		this.messageKey = messageKey;
	}


	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}


	public UUID getId() {
		return id;
	} 
	
	
	
}
