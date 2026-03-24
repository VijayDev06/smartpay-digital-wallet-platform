package com.service;

import java.util.Map;
import java.util.function.Function;

import com.security.CustomUserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
	
	String extractUsername(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	String generateToken(CustomUserService userDetails);

	String generateToken(Map<String, Object> extraClaims, CustomUserService userDetails);

	long getExpirationTime();

	boolean isTokenValid(String token, CustomUserService userDetails);

	String extractTokenFromRequest(HttpServletRequest request);
}
