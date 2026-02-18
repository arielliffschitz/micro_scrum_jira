package com.ariel.mscrumjira;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.*;
import org.springframework.test.context.jdbc.Sql.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.service.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/schema-test.sql", "/data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class SprintIntegrationTest {
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";
	
	@Autowired
	private SprintService service;
	
	@SuppressWarnings("removal")
	@MockBean
	private UserFeignClient userClient;
	
	SprintCreateDto createDto;

	@BeforeEach
	void setUp() throws Exception {
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.now().plusDays(14);
		createDto = new SprintCreateDto(1, "super team", startDate,endDate );
	}

	@DisplayName("Find By SprintKey success")
	@Test
	void testFindBySprintKeyOK() {
		Integer sprintKey = 101;

		SprintDto result = service.findBySprintKey(sprintKey);

		assertThat(result.state()).isEqualTo(SprintState.PLANNED);
		assertThat(result.sprintKey()).isNotNull();
	}
	
	@DisplayName("Find By  SprintKey fail")
	@Test
	void testFindBySprintKeyNotOK() {
		Integer sprintKey = 1;

		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> service.findBySprintKey(sprintKey));
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);		
	}

	@DisplayName("Create sprint success")
	@Test
	void testCreateSprintOk() {	
		String teamKey ="super team";
		
		when(userClient.existsByTeamKey(teamKey)).thenReturn(true);			

		UUID idResult = service.create(createDto, token);
		SprintDto result = service.findById(idResult);

		assertThat(result.teamKey()).isEqualTo(teamKey);
		assertThat(result.sprintKey()).isNotNull();
		assertThat(result.state()).isEqualTo(SprintState.PLANNED);
	}
	@DisplayName("Create sprint fail doesn't exist teamKey")
	@Test
	void testCreateSprintFail() {	
		String teamKey ="super team";
		
		when(userClient.existsByTeamKey(teamKey)).thenReturn(false);			

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> service.create(createDto, token));
		assertThat(exception.getMessage()).isEqualTo("The team: "+teamKey+ " doesn't exist");				
	}

}
