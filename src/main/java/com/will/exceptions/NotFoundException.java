package com.will.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ExceptionError {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message) {
		
		super(message, HttpStatus.NOT_FOUND);
	}

}
