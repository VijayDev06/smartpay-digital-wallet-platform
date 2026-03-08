package com.service;

import com.dto.RegisterRequest;
import com.dto.UserResponse;

public interface AuthenticationService {

	UserResponse registerUser(RegisterRequest registerRequest);

}
