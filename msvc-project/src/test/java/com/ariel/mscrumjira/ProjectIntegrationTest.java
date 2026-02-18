package com.ariel.mscrumjira;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.*;
import org.springframework.test.context.jdbc.Sql.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.service.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/schema-test.sql", "/data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class ProjectIntegrationTest {

	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";

	@Autowired
	private ProjectService service;

	@DisplayName("find By ProjectKey with associated Sprint  success")
	@Test
	public void testfindByProjectKeyOKWithSprint() {
		Integer projectKey = 1;

		ProjectDto result = service.findByProjectKey(projectKey);

		assertThat(result.getName()).isEqualTo("Project Alpha");
		assertThat(result.getSprints().get(0).state()).isEqualTo(SprintState.PLANNED);
		assertThat(result.getSprints().size()).isEqualTo(2);
	}

	@DisplayName("find By ProjectKey that doesn't exist")
	@Test
	public void testfindByProjectKeyNotExist() {
		Integer projectKey = 4;
		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> service.findByProjectKey(projectKey));
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@DisplayName("find By ProjectKey without associated Sprint  success")
	@Test
	public void testfindByProjectKeyOKNotSprints() {
		Integer projectKey = 3;

		ProjectDto result = service.findByProjectKey(projectKey);

		assertThat(result.getName()).isEqualTo("Project Gamma");
		assertThat(result.getSprints().size()).isEqualTo(0);
	}

	@DisplayName("Create project  success")
	@Test
	public void testCreateProjectOk() {
		ProjectCreateDto createDto = new ProjectCreateDto("new project", "description for new project");

		UUID idResult = service.create(createDto, token);
		ProjectDto result = service.findById(idResult);

		assertThat(result.getName()).isEqualTo("new project");
		assertThat(result.getProjectKey()).isNotNull();
		assertThat(result.getProjectState()).isEqualTo(ProjectState.CREATED);
	}

}
