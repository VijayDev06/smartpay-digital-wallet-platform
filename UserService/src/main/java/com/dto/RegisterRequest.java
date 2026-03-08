package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterRequest {
	
	@Schema(example = "vijay321")
	@Size(min = 4, max = 20)
	@NotBlank(message = "Username cannot be empty")
	@JsonProperty(value = "username")
	private String username;

	@Schema(example = "vijay@gmail.com")
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	@JsonProperty(value = "email")
	private String email;

	@Schema(example = "vijay")
	@NotBlank(message = "First name cannot be empty")
	@JsonProperty(value = "firstName")
	private String firstName;

	private String middleName;

	@Schema(example = "kapse")
	@NotBlank(message = "Last name cannot be empty")
	@JsonProperty(value = "lastName")
	private String lastName;

	@Schema(example = "+91234567890")
	@NotBlank(message = "Mobile number cannot be empty")
	@Pattern(regexp = "(^$|\\+\\d{1,3}\\s?\\d{1,12})", message = "Invalid contact number. Please provide a valid mobile number.")
	@JsonProperty(value = "mobileNumber")
	private String mobileNumber;

	@Schema(example = "Password@123!")
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@JsonProperty(value = "password")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
	private String password;

}
