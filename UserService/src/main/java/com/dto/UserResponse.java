package com.dto;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Validated
public class UserResponse {
	
	private String username;

	private String email;

	private String firstName;

	private String middleName;

	private String lastName;

	private String mobileNumber;

	private String password;
}
