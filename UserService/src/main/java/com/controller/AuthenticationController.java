package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RegisterRequest;
import com.dto.UserResponse;
import com.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User successfully registered"),
	@ApiResponse(responseCode = "400", description = "Bad Request - Invalid input") })
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest){
		
		log.info("Signup request received for email: {}", registerRequest.getEmail());
		log.info("Done gone");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(authService.registerUser(registerRequest));
		
	}
	
	

}
