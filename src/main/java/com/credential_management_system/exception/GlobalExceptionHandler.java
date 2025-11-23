package com.credential_management_system.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ IllegalArgumentException.class, TemplateAlreadyExistsException.class,
			TemplateNotFoundException.class })
	public ResponseEntity<?> handleCustomExceptions(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("BAD_REQUEST", e.getMessage()));
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<?> handleClientNotFoundException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("NOT_FOUND", e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred"));
	}
}