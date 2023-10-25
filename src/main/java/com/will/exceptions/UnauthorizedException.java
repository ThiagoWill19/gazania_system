package com.will.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ExceptionError {

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String message) {
		
		super(message, HttpStatus.UNAUTHORIZED);
	}

}
