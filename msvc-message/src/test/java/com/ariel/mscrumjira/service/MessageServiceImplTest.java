package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;
import com.ariel.mscrumjira.repository.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

	@Mock
	private MessageRepository repository;
	@Mock
	private UserFeignClient userClient;
	
	@InjectMocks
	private  MessageServiceImpl service;
	
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";
	
	private Message dao;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = new Message();
		dao.setSubject("test subject");
	}
	@DisplayName("find by messageKey success")
	@Test
	void testFindByMessageKeyOk() {
		Integer messageKey =1;
		
		when(repository.findByMessageKey(messageKey)).thenReturn(Optional.of(dao));
		
		MessageDto result = service.findByMessageKey(messageKey);
		
		assertThat(result.subject()).isEqualTo("test subject");
		assertThat(result.readFlag()).isTrue();
		
		verify(repository).save(eq(dao));
	}
	@DisplayName("find by messageKey Not Found")
	@Test
	void testFindByMessageKeyNotFound() {
		Integer messageKey =1;
		
		when(repository.findByMessageKey(messageKey)).thenReturn(Optional.empty());		
	
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.findByMessageKey(messageKey));		
	
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(exception.getReason()).isEqualTo("Message not found");
		
		verify(repository,never()).save(dao);
	}

}
