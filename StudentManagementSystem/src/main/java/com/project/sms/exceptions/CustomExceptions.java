package com.project.sms.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomExceptions extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	public CustomExceptions(String message) {
		super(message);
	}	
	public CustomExceptions(String message,Throwable throwable) {
		super(message,throwable);
	}

}
