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
import com.ariel.mscrumjira.domain.enums.ProjectState;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.Project;
import com.ariel.mscrumjira.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectUpdateServiceTest {

	@Mock
	private ProjectRepository repository;
	@Mock
	private ProjectSprintService projectSprintService;
	@Mock
	private AuditFeignClient auditClient;	

	Project project;
	
	ProjectUpdateDto projectUpdateDto;

	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";

	@InjectMocks 
	private ProjectUpdateService  service;

	@BeforeEach
	void setUp() throws Exception {

		project = new Project("testName","descriptionTest");
		Field field = Project.class.getDeclaredField("projectKey"); 
		field.setAccessible(true); 
		field.set(project, 1);
		project.setState(ProjectState.CREATED);     
		
		
	}
	@DisplayName("Update project to ARCHIVED state with no active sprints")
	@Test
	void testUpdateProjectStateArchivedNoActiveSprints() {
		Integer projectKey = 1;
		projectUpdateDto = new ProjectUpdateDto("Project title", "Project description", ProjectState.ARCHIVED);		

		when(repository.findByProjectKey(projectKey)).thenReturn(Optional.of(project));		
		when(projectSprintService.existSprintByProjectKey(projectKey)).thenReturn(false);		

		ProjectDto result =service.update(projectKey, projectUpdateDto, token);
		
		assertThat(result.getProjectState()).isEqualTo(ProjectState.ARCHIVED);
		assertThat(result.getName()).isEqualTo("Project title");
		
		verify(auditClient).createProject(any(), eq(token));		
		verify(repository).delete(eq(project));		
		verify(repository,never()).save(project);						
	}

	@DisplayName("updateState with ARCHIVED and with active sprints")
	@Test
	void testUpdateStateArchivedActiveSprints() {
		Integer projectKey = 1;
		projectUpdateDto = new ProjectUpdateDto("Project title", "Project description", ProjectState.ARCHIVED);		

		when(repository.findByProjectKey(projectKey)).thenReturn(Optional.of(project));				

		when(projectSprintService.existSprintByProjectKey(projectKey)).thenReturn(true);

		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.update(projectKey, projectUpdateDto, token)); 
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);		

		verify(auditClient,never()).createProject(any(), eq(token));		
		verify(repository,never()).delete(eq(project));		
		verify(repository,never()).save(project);				
	}

	@DisplayName("updateState with CREATED ")
	@Test
	void testUpdateStateCreated() {
		Integer projectKey = 1;
		projectUpdateDto = new ProjectUpdateDto("Project title", "Project description", ProjectState.CREATED);
		
		when(repository.findByProjectKey(projectKey)).thenReturn(Optional.of(project));		
		
		ProjectDto result = service.update(projectKey, projectUpdateDto, token);

		assertThat(result.getProjectState()).isEqualTo(ProjectState.CREATED);

		verify(auditClient,never()).createProject(any(), eq(token));		
		verify(repository,never()).delete(project);		
		verify(repository).save(eq(project));					
	}

	@DisplayName("updateState with not find the project ")
	@Test
	void testUpdateStateNotFindProject() {
		Integer projectKey = 1;
		projectUpdateDto = new ProjectUpdateDto("Project title", "Project description", ProjectState.CREATED);
		
		when(repository.findByProjectKey(projectKey)).thenReturn(Optional.empty());			
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.update(projectKey, projectUpdateDto, token)); 
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
