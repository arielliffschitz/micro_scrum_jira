package com.ariel.mscrumjira.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.mscrumjira.entity.*;

public interface MessageRepository extends JpaRepository<Message, UUID> {

	List<Message> findByReceiverAndReadFlag(String receiver, boolean readFlag);

	List<Message> findByCreatedBy(String createdBy);

	Optional<Message> findByMessageKey(Integer messageKey);

	void deleteByMessageKey(Integer messageKey);
}
