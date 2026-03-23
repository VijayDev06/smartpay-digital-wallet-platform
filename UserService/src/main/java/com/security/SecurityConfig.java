package com.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configurable
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	private final JwtAuthenticationFilter jwtAuthenticationFilter;
//	private final AuthenticationProvider authenticationProvider;
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/api/v1/auth/**", "/api/v1/users/**")
//						.permitAll().requestMatchers("/api/v1/admin/**").authenticated()
//						.anyRequest().authenticated())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authenticationProvider(authenticationProvider)
//				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//				.cors(cors -> {
//				}); // enable default CORS
//		
//		// CORS
//		// can
//		// be
//		// configured
//		// further
//		// if
//		// needed
//
//		log.info("Security configuration loaded successfully.");
//
//		return http.build();
	
}
