package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.charset.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.dto.*;

import feign.*;

@ExtendWith(MockitoExtension.class)
class TaskFinderServiceTest {
	@Mock
	private  ProductBacklogFeignClient clientProduct;
	@Mock
	private  SprintBacklogFeignClient  clientSprint;  
	@Mock
	private  AuditFeignClient  clientAudit;	
	
	@InjectMocks 
	private TaskFinderService service;
	
	ProductBacklogItemDto productDto;
	
	@BeforeEach
	void setUp() throws Exception {
		productDto = new ProductBacklogItemDto();
		productDto.setTitle("Test Junit");
		productDto.setPriority(5);
		productDto.setEstimate(10);
		productDto.setProjectKey(1);
		
	}

	@DisplayName("Find task in productBackLog with success")
	@Test
	void testFindInProductOk() {
		Integer taskNumber = 1;
		
		when(clientProduct.findByTaskNumber(taskNumber)).thenReturn(productDto);
		
		ProductBacklogItemDto result = service.findInProduct(taskNumber);
		
		assertThat(result.getEstimate()).isEqualTo(10);
		
	}
	@SuppressWarnings("deprecation")
	@DisplayName("Find task in productBackLog without success")
	@Test
	void testFindInProductFail() {
		Integer taskNumber = 1;
		
		when(clientProduct.findByTaskNumber(taskNumber))
	    .thenThrow(new FeignException.NotFound("Not Found", 
	        Request.create(Request.HttpMethod.GET, "", Collections.emptyMap(), null, Charset.defaultCharset()), 
	        null, Collections.emptyMap()));
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.findInProduct(taskNumber)); 
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
	}

}
