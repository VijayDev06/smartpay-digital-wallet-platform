package com.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserService extends UserDetails {

	String getEmail();
	
}
