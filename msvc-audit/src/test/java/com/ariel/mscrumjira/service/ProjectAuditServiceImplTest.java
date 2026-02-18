package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.entity.*;
import com.ariel.mscrumjira.repository.*;

@ExtendWith(MockitoExtension.class)
class ProjectAuditServiceImplTest {

	@Mock
	private ProjectAuditRepository projectRepository;	
	@Mock
	private SprintAuditRepository  sprintRepository;
	
	private ProjectAudit projectAudit;
	
	@InjectMocks 
	private ProjectAuditServiceImpl service ;
	
	@BeforeEach
	void setUp() throws Exception {
		projectAudit = new ProjectAudit(3,"name project", "description");
	}

	@DisplayName("Find By ProjectKey with success")
	@Test
	void testFindByProjectKeyOk() {
		Integer projectKey = 6;
		List<SprintAudit> SprintAudioList = new ArrayList<>(List.of(new SprintAudit()));
		
		when(projectRepository.findByProjectKey(projectKey)).thenReturn(Optional.of(projectAudit));
		when(sprintRepository.findByProjectKey(projectKey)).thenReturn(SprintAudioList);
		
		ProjectAuditDto result = service.findByProjectKey(projectKey);
		
		assertThat(result.getDescription()).isEqualTo("description");
		assertThat(result.getProjectKey()).isEqualTo(3);
		assertThat(result.getSprints().size()).isEqualTo(1);
		
		verify(projectRepository).findByProjectKey(eq(projectKey));				
	}
	
	@DisplayName("Find By ProjectKey without success")
	@Test
	void testFindByProjectKeyNotOk() {
		Integer projectKey = 6;		
		
		when(projectRepository.findByProjectKey(projectKey)).thenReturn(Optional.empty());
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.findByProjectKey(projectKey));			
		
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(exception.getReason()).isEqualTo("ProjectKey: " +projectKey+ " not found");
		
		verify(projectRepository).findByProjectKey(eq(projectKey));
		verify(sprintRepository, never()).findByProjectKey(projectKey);
	}

}
