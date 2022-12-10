package br.com.joel.exceptions;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1l;
	
	private InvalidJwtAuthenticationException(String message) {
		super(message);
	}

}
