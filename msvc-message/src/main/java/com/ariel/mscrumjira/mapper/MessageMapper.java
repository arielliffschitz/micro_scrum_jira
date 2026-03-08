package com.ariel.mscrumjira.mapper;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;

public class MessageMapper {
	
	public static MessageDto mapToDto(Message dao) {
		return new MessageDto(				
				dao.getMessageKey(),
				dao.getReceiver(),
				dao.getSubject(),
				dao.getContent(),
				dao.isReadFlag(),
				dao.getCreatedBy(),
				dao.getCreatedAt()								
				);
	}
	
	public static MessageListDto mapToListDto(Message dao) {
		return new MessageListDto(				
				dao.getMessageKey(),
				dao.getReceiver(),
				dao.getSubject(),				
				dao.isReadFlag(),
				dao.getCreatedBy(),
				dao.getCreatedAt()								
				);
	}

	public static Message mapFromCreateToDao(MessageCreateDto dto) {		
		return new Message(
				dto.receiver(),
				dto.subject(),
				dto.content()
				);
	}

}
