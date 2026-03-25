package com.serviceImpl;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.LoginRequest;
import com.dto.LoginResponse;
import com.dto.RegisterRequest;
import com.dto.UserResponse;
import com.entity.Role;
import com.entity.User;
import com.enums.KycStatusEnum;
import com.enums.RoleEnum;
import com.enums.UserStatusEnum;
import com.exception.ResourceFoundException;
import com.exception.ResourceNotFoundException;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.AuthenticationService;
import com.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private RoleRepository rr;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService; // genrate token method

	@Override
	public UserResponse registerUser(RegisterRequest registerRequest) {

		log.info("Registering new user with email: {}", registerRequest.getEmail());

		ur.findByEmail(registerRequest.getEmail()).ifPresent((user) -> {
			throw new ResourceFoundException("User with this email already exists");
		});

		ModelMapper model = new ModelMapper();
		User user = model.map(registerRequest, User.class);

		log.debug("User from db" + user);

		user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encoding password Password 
		user.setKycStatus(KycStatusEnum.PENDING);
		user.setStatus(UserStatusEnum.ACTIVE);

		Role role = rr.findByRolename(RoleEnum.USER)
				.orElseThrow(() -> new ResourceNotFoundException("Default role not found"));

		user.setRole(role);

		User savedUser = ur.save(user);

		UserResponse uResponse = model.map(savedUser, UserResponse.class);

//		UserResponse uResponse = new UserResponse();
//		uResponse.setFirstName(registerRequest.getFirstName());
//		uResponse.setEmail(registerRequest.getEmail());
//		uResponse.setLastName(registerRequest.getLastName());
//		uResponse.setMiddleName(registerRequest.getMiddleName());
//		uResponse.setMobileNumber(registerRequest.getMobileNumber());
//		uResponse.setPassword(registerRequest.getPassword());
//		uResponse.setUsername(registerRequest.getUsername());

		return uResponse;
	}

	// Email checking -> Token Genration
	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) {

		log.info("Request Info" + loginRequest);

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		User authenticatedUser = ur.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException(loginRequest.getEmail()));

		Instant currenrDate = Instant.now();

		User updatedUser = ur.save(authenticatedUser);

		String jwtToken = jwtService.generateToken(updatedUser);

		log.info("Jwt Token Genrated : " + jwtToken);

		return LoginResponse.builder().userId(updatedUser.getId().toString()).token(jwtToken)
				.expiryAt(currenrDate.plusMillis(jwtService.getExpirationTime()).toString()).build();
	}

}
