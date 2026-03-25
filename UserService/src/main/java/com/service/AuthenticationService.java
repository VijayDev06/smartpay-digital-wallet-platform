package com.service;

import com.dto.LoginRequest;
import com.dto.LoginResponse;
import com.dto.RegisterRequest;
import com.dto.UserResponse;

public interface AuthenticationService {

	UserResponse registerUser(RegisterRequest registerRequest);

	LoginResponse authenticate(LoginRequest loginRequest);

}
