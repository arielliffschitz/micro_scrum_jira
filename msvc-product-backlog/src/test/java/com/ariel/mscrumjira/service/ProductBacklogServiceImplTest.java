package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.entity.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.repository.*;

@ExtendWith(MockitoExtension.class)
class ProductBacklogServiceImplTest {

	@Mock
	private ProductBacklogRepository repository;
	@Mock
	private ProjectFeignClient projectClient;
	
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";
	
	@InjectMocks 
	private ProductBacklogServiceImpl service;
	
	private ProductCreateDto dto;
	private ProductBacklogItem dao; 
	private ProductBacklogItem savedDao;
	
	private  UUID id;
	@BeforeEach
	void setUp() throws Exception {
		 dto = new ProductCreateDto("title product", "description product",3,6,1);
		 
		 dao = new ProductBacklogItem();
		 dao.setProjectKey(1);
		 dao.setTitle("daoTitle");
		 dao.setDescription("daoDescription");
		 dao.setProjectKey(3);
		 
		 id = UUID.fromString("3f9b2d1e-7c4a-4f8e-9b2a-5d6e1a7c8f90");
		 savedDao = mock(ProductBacklogItem.class);
		 Field field = ProductBacklogItem.class.getDeclaredField("id");
		 field.setAccessible(true);
		 field.set(savedDao, id);
		
		  

	}

	@DisplayName("Create product backlog item when project exists")	
	@Test
	void testCreateProductBackloWwhenProjectExists() {
		when(projectClient.existsByTeamKey(dto.projectKey())).thenReturn(true);
		when(savedDao.getId()).thenReturn(id);
		when(repository.save(any(ProductBacklogItem.class))).thenReturn(savedDao);
		
		 UUID result = service.create(dto, token);
		 
		 verify(repository).save(any(ProductBacklogItem.class));
		 assertThat(result).isEqualTo(id);		 		 
		
	}
	@DisplayName("Create product backlog item when project doesn't exists")	
	@Test
	void testCreateProductBackloWwhenProjectNotExists() {
		when(projectClient.existsByTeamKey(dto.projectKey())).thenReturn(false);		
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.create(dto, token)); 
		
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);		
		 
		 verify(repository,never()).save(any(ProductBacklogItem.class));
		 verify(projectClient).existsByTeamKey(dto.projectKey());
	}
	
	@DisplayName("Update backlog item when taskNumber exists and return updated DTO")
	@Test
	void updateItemWhenTaskNumberExists() {
		Integer taskNumber=1;
		UpdateSprintBacklogDto updateDto = new UpdateSprintBacklogDto("title", "description", null, null, null);
		
		when(repository.findByTaskNumber(taskNumber)).thenReturn(Optional.of(dao));
		when(repository.save(any(ProductBacklogItem.class))).thenReturn(dao);
		
		ProductBacklogItemDto result = service.update(taskNumber, updateDto, token);
		
		assertThat(result.getDescription()).isEqualTo("description");				
	}

}
