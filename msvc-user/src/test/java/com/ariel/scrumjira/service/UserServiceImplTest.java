package com.ariel.scrumjira.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.server.*;

import com.ariel.mscrumjira.domain.enums.*;
import com.ariel.mscrumjira.dto.*;
import com.ariel.scrumjira.dto.*;
import com.ariel.scrumjira.entity.*;
import com.ariel.scrumjira.repository.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	@Mock
	private  UserRepository userRepository;
	@Mock
	private  RoleRepository roleRepository;  
	@Mock
	private  PasswordEncoder passwordEncoder;
	
	final String token = "Bearer eyJraWQiOiIyOTE4NmRjZC01YzE1LTRkZDItYTJjZC02NGIwYjNmOGEyY2EiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImF1ZCI6ImdhdGV3YXktYXBwIiwibmJmIjoxNzY4NzY5Njk5LCJkYXRhIjoiZGF0YSBhZGljaW9uYWwgZW4gZWwgdG9rZW4iLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDg1IiwiZXhwIjoxNzY4Nzg0MDk5LCJpYXQiOjE3Njg3Njk2OTksImp0aSI6IjBlMTdlY2YyLWY5OTEtNDQwYy1hM2NhLTI0M2Q5NjE3ODg4MCJ9.rAM7i8JTDqbgTAuWS6LNF9K2JNnIiJgA3zJ8IaCH09gdpU568H2s9e4yFBWVkd_VbRTCBaldhu3tyuY9A0aLHRPl7mm-OmC6Bq4yBGe11VtjJSWyKK4wNhwvpBkO6SFgr85u3DvhKGYBlefRdyOFhOGWfypvzYpoD7yHttM4SaWrblPxy7dWfi_V-14xt40l9_z9qgewWDR2uqcvbKawXL0iBgO2Td5uuIers47Ym6PFb9igxO4LsRX4n7A67u4c8c1B90W5DYX3fh_qeBEsJANxwmids04cRqxjAcpNu2aaUCkNwUYEPcMhNDs19ruWyrLmVDhebyeMImsei7f39Q";
	
	private User user;

	@InjectMocks 
	private UserServiceImpl service;
	private Set<Role> roles;

	@BeforeEach
	void setUp() throws Exception {
		roles = Set.of(new Role(RoleName.ADMIN));
		user = new User("ariel@xmen.com","12345", "ariel",roles);				
	}
	
	@DisplayName("create with success")
	@Test
	void testCreateOk() {
		List<Role> rolesList = new ArrayList<>(List.of(new Role(RoleName.ADMIN)));
		Set<RoleName>roleNames  = Set.of(RoleName.ADMIN,RoleName.USER);
		
		UserCreateDto userCreateDto = new UserCreateDto();
		userCreateDto.setDisplayName("display");
		userCreateDto.setPassword("12345");
		userCreateDto.setRoles(roleNames);
		
		when(roleRepository.findByNameIn(roleNames)).thenReturn(rolesList);
		when(passwordEncoder.encode("12345")).thenReturn("$2a$10$qv1hkd92QBGCU8uUpDN1DeyXuju07TqWCOmz6GijmLlAnJlh3Le1C");
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		UserDto result = service.create(userCreateDto, token);
		
		assertThat(result.getDisplayName()).isEqualTo("ariel");
		
	}

	@DisplayName("Find For Login By Username success")
	@Test
	void testFindForLoginByUsernameOk() {
		String username = "ariel@xmen.com";
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		UserLoginDto result = service.findForLoginByUsername(username);
		
		verify(userRepository, times(1)).save(any(User.class));
		
		assertThat(result.getPassword()).isEqualTo("12345");
		assertThat(user.getLastLogin()).isNotNull();
	}
	
	
	@DisplayName("Find For Login By Username fail")
	@Test
	void testFindForLoginByUsernameNotOk() {
		String username = "ariel@xmen.com";
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
		
		ResponseStatusException  exception = assertThrows(ResponseStatusException.class,
				() -> service.findForLoginByUsername(username)); 
		
		assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(exception.getReason()).isEqualTo("User with username: " + username + " not found");

		assertThat(user.getLastLogin()).isNull();
		
		verify(userRepository, never()).save(any(User.class));				
	}

}
