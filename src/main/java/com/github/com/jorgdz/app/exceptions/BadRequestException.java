package com.github.com.jorgdz.app.exceptions;

public class BadRequestException extends RuntimeException{
	
	private static final long serialVersionUID = 8731055296010452074L;

	public BadRequestException(String context) {
		super(context);
	}
}
