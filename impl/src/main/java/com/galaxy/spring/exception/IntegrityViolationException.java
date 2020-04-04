package com.galaxy.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IntegrityViolationException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public IntegrityViolationException(String message, Exception exception) {
		super(message, exception);
	}

}
