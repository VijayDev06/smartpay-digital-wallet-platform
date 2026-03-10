package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dto.ErrorHandlingResponse;


@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(ResourceFoundException.class)
	private ResponseEntity<ErrorHandlingResponse> handleAllErrors(ResourceFoundException re) {
		
		 return new ResponseEntity<>(
				 ErrorHandlingResponse.builder().message(re.getMessage())
				 .build(), HttpStatus.CONFLICT);  //// 409 - Duplicate / already exists

	}
	
	
	
	
	/**
	// This Will create new exception every time 
	@ExceptionHandler(ResourceFoundException.class)
	public ResponseEntity<ErrorHandlingResponse> ResourceNotFoundHandle(ResourceFoundException rfe){
		
		return new ResponseEntity<>(new ErrorHandlingResponse().builder().msg(rfe.getMessage()).build(), HttpStatus.NOT_FOUND);
	}
	*/

}
