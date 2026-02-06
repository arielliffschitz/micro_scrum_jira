package com.ariel.mscrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.ariel.mscrumjira.client.*;
import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.mscrumjira.mapper.*;

@ExtendWith(MockitoExtension.class)
class TaskMoveServiceTest {
	@Mock
	private  ProductBacklogFeignClient clientProduct;
	@Mock
	private  SprintBacklogFeignClient  clientSprint;
	@Mock
	private  AuditFeignClient  clientAudit;
	@Mock
	private TaskFinderService finderService;
	
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";
	
	ProductBacklogItemDto productDto;
	SprintBacklogItemDto sprintDto;
	TaskMoveSprintRequestDto taskMoveSprintRequestDto;

	@InjectMocks 
	private TaskMoveService service;
	
	@BeforeEach
	void setUp() throws Exception {
		productDto = new ProductBacklogItemDto();
		productDto.setTitle("Test Junit");
		productDto.setPriority(5);
		productDto.setEstimate(10);
		productDto.setProjectKey(1);
		
		sprintDto = new SprintBacklogItemDto();
		sprintDto.setEstimate(11);
		sprintDto.setPriority(6);
		
		taskMoveSprintRequestDto = new TaskMoveSprintRequestDto(1,1);
	}

	@DisplayName("Move From Product To Sprint  with success")
	@Test
	void testMoveFromProductToSprintOk() {
		Integer taskNumber = 1;
		Integer sprintKey = 1;
		
		when(finderService.findInProduct(taskNumber)).thenReturn(productDto);	
		
		SprintBacklogItemDto  sprintDto  = TaskItemMapper.toSprintDtoFromProductDto(productDto);
		
		doNothing().when(clientSprint).save(any(SprintBacklogItemDto.class), anyString());
		doNothing().when(clientProduct).deleteProductByTaskNumber(taskNumber);
		doNothing().when(clientAudit).createMovement(new TaskMovementAuditCreateDto(
													taskNumber, AuditTaskState.MOVE_TO_SPRINT), token);
		
		sprintDto.setSprintKey(sprintKey);
		
		SprintBacklogItemDto result = service.moveFromProductToSprint(taskMoveSprintRequestDto, token);

		assertThat(result.getEstimate()).isEqualTo(10);
		assertThat(result.getSprintKey()).isEqualTo(1);
		
		verify(finderService).findInProduct(eq(taskNumber));
		verify(clientSprint).save(any(SprintBacklogItemDto.class), anyString());
		verify(clientProduct).deleteProductByTaskNumber(eq(taskNumber));
		verify(clientAudit).createMovement(new TaskMovementAuditCreateDto(
				taskNumber, AuditTaskState.MOVE_TO_SPRINT), token);
	}
	@DisplayName("Move From Product To Sprint  with fail")
	@Test
	void testMoveFromProductToSprintfail() {
		Integer taskNumber = 1;
		Integer sprintKey = 1;
		
		when(finderService.findInProduct(taskNumber)).thenReturn(productDto);	
		
		SprintBacklogItemDto  sprintDto  = TaskItemMapper.toSprintDtoFromProductDto(productDto);
		
		doThrow(new RuntimeException("error saving sprint")).when(clientSprint).save(any(SprintBacklogItemDto.class), anyString());
			
		sprintDto.setSprintKey(sprintKey);				
		
		assertThrows(RuntimeException.class, () ->
        service.moveFromProductToSprint(taskMoveSprintRequestDto, token));

		verify(finderService).findInProduct(eq(taskNumber));
		verify(clientSprint).save(any(SprintBacklogItemDto.class), anyString());
		verify(clientProduct,never()).deleteProductByTaskNumber(eq(taskNumber));
		verify(clientAudit,never()).createMovement(new TaskMovementAuditCreateDto(
				taskNumber, AuditTaskState.MOVE_TO_SPRINT), token);
	}
	
	@DisplayName("Move From  Sprint To Product with success")
	@Test
	void testMoveFromSprintToProductOk() {
		Integer taskNumber = 1;		
		
		when(finderService.findInSprint(taskNumber)).thenReturn(sprintDto);	
		when(clientProduct.save(any(ProductBacklogItemDto.class), anyString())).thenReturn(productDto);					
		
		doNothing().when(clientSprint).deleteByTaskNumber(taskNumber);
		doNothing().when(clientAudit).createMovement(any(TaskMovementAuditCreateDto.class), anyString());				
		
		ProductBacklogItemDto result = service.moveFromSprintToProduct(taskNumber, token);

		assertThat(result.getEstimate()).isEqualTo(10);
		assertThat(result.getPriority()).isEqualTo(5);
		
		verify(finderService).findInSprint(eq(taskNumber));
		verify(clientProduct).save(any(ProductBacklogItemDto.class), anyString());
		verify(clientSprint).deleteByTaskNumber(eq(taskNumber));
		verify(clientAudit).createMovement(any(TaskMovementAuditCreateDto.class), anyString());
	}
	@DisplayName("Move From  Sprint To Product with Fail")
	@Test
	void testMoveFromSprintToProductFail() {
		Integer taskNumber = 1;		
		
		when(finderService.findInSprint(taskNumber)).thenReturn(sprintDto);	
		
		when(clientProduct.save(any(ProductBacklogItemDto.class), anyString()))
				.thenThrow(new RuntimeException("error saving product"));							
		
		assertThrows(RuntimeException.class, () ->
        service.moveFromSprintToProduct(taskNumber, token));				
		
		verify(finderService).findInSprint(eq(taskNumber));
		verify(clientProduct).save(any(ProductBacklogItemDto.class), anyString());
		verify(clientSprint,never()).deleteByTaskNumber(eq(taskNumber));
		verify(clientAudit,never()).createMovement(any(TaskMovementAuditCreateDto.class), anyString());
	}

}
