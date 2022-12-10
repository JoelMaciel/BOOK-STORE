package br.com.joel.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.joel.exceptions.ExceptionsResponse;
import br.com.joel.exceptions.InvalidJwtAuthenticationException;
import br.com.joel.exceptions.RequiredObjectIsNullException;
import br.com.joel.exceptions.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionsResponse> handleAllException(Exception e, WebRequest request) {
		
		var exceptionResponse = new ExceptionsResponse(
				new Date(), e.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionsResponse> handleNotFoundException(Exception e, WebRequest request) {
		
		var exceptionResponse = new ExceptionsResponse(
				new Date(), e.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionsResponse> handleBadRquestException(Exception e, WebRequest request) {
		
		var exceptionResponse = new ExceptionsResponse(
				new Date(), e.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionsResponse> HandleInvalidJwtAuthenticationException(Exception e, WebRequest request) {
		
		var exceptionResponse = new ExceptionsResponse(
				new Date(), e.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

}
