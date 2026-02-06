package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ariel.mscrumjira.client.AuditFeignClient;
import com.ariel.mscrumjira.domain.enums.SprintState;
import com.ariel.mscrumjira.dto.SprintDto;
import com.ariel.mscrumjira.entity.Project;
import com.ariel.mscrumjira.entity.Sprint;
import com.ariel.mscrumjira.repository.SprintRepository;

@ExtendWith(MockitoExtension.class)
class SprintServiceImplTest {

	@Mock
	private SprintRepository repository;
	@Mock
	private ProjectSprintService projectSprintService;
	@Mock
	private AuditFeignClient auditClient;	
	
	Sprint sprint;
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";

	@InjectMocks 
	private SprintServiceImpl  service;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		Project project = new Project();
		Field fieldProject = Project.class.getDeclaredField("projectKey"); 
		fieldProject.setAccessible(true); 
		fieldProject.set(project, 1);		
		
		sprint = new Sprint();
		sprint.setState(SprintState.PLANNED);
		sprint.setProject(project);
		Field field = Sprint.class.getDeclaredField("sprintKey"); 
		field.setAccessible(true); 
		field.set(sprint, 1);
	}

	@DisplayName("updateState with ARCHIVED")
	@Test
	void testUpdateStateArchived() {
		Integer sprintKey = 1;
		SprintState state = SprintState.ARCHIVED;
		
		when(repository.findBySprintKey(sprintKey)).thenReturn(Optional.of(sprint));
		sprint.setState(state);
		PersistenceMetadataUtil.BaseEntityUpdateFields(sprint, token);
		
		SprintDto result = service.updateState(sprintKey, state, token);
		
		assertThat(result.state()).isEqualTo(SprintState.ARCHIVED);				
		
		verify(auditClient).createSprint(any(), eq(token));
		verify(repository).delete(eq(sprint));
		verify(repository,never()).save(sprint);		
	}
	
	@DisplayName("updateState with COMPLETED")
	@Test
	void testUpdateStateCompleted() {
		Integer sprintKey = 1;
		SprintState state = SprintState.COMPLETED;
		
		when(repository.findBySprintKey(sprintKey)).thenReturn(Optional.of(sprint));
		sprint.setState(state);
		PersistenceMetadataUtil.BaseEntityUpdateFields(sprint, token);
		
		SprintDto result = service.updateState(sprintKey, state, token);
		
		assertThat(result.state()).isEqualTo(SprintState.COMPLETED);				
		
		verify(auditClient,never()).createSprint(any(), eq(token));
		verify(repository,never()).delete(sprint);
		verify(repository).save(eq(sprint));		
	}
	
	@DisplayName("updateState with not find the sprint")
	@Test
	void testUpdateStateNotFindSprint() {
		Integer sprintKey = 1;
		SprintState state = SprintState.COMPLETED;
		
		when(repository.findBySprintKey(sprintKey)).thenReturn(Optional.empty());

		sprint.setState(state);
		PersistenceMetadataUtil.BaseEntityUpdateFields(sprint, token);				
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.updateState(sprintKey, state, token)); 
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);			
			
	}


}
