package com.ariel.mscrumjira.service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ariel.mscrumjira.domain.enums.RoleName;

@Service
public class JWTService {
	
	
    private final JwtEncoder jwtEncoder;

    public JWTService( JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(String username, Set<RoleName> rolesEnum) {
        Instant now = Instant.now();
        long expiry = 36000L; // 10 horas en segundos
        List<String>roles = mapToString(rolesEnum);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("msvc-oauth")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(username)
                .claim("roles", roles)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

	private List<String> mapToString(Set<RoleName> rolesEnum) {
		return  rolesEnum
                .stream()
                .map(RoleName::name)
                .collect(Collectors.toList());
	}
}

