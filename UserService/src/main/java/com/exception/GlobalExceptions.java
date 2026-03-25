package com.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dto.ErrorHandlingResponse;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(ResourceFoundException.class)
	private ResponseEntity<ErrorHandlingResponse> handleAllErrors(ResourceFoundException re) {

		return new ResponseEntity<>(ErrorHandlingResponse.builder().message(re.getMessage()).build(),
				HttpStatus.CONFLICT); //// 409 - Duplicate / already exists

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentValidException(MethodArgumentNotValidException msg) {

		Map<String, String> errors = new HashMap<>();

		List<FieldError> fieldErrors = msg.getBindingResult().getFieldErrors();

		for (FieldError e : fieldErrors) {
			errors.put(e.getField(), e.getDefaultMessage());
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	/**
	 * // This Will create new exception every time
	 * 
	 * @ExceptionHandler(ResourceFoundException.class) public
	 *                                                 ResponseEntity<ErrorHandlingResponse>
	 *                                                 ResourceNotFoundHandle(ResourceFoundException
	 *                                                 rfe){
	 * 
	 *                                                 return new
	 *                                                 ResponseEntity<>(new
	 *                                                 ErrorHandlingResponse().builder().msg(rfe.getMessage()).build(),
	 *                                                 HttpStatus.NOT_FOUND); }
	 */

}
