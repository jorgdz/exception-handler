package com.github.com.jorgdz.app.exceptions;

public class ConflictException extends RuntimeException{

	private static final long serialVersionUID = 3102454306822171794L;
	
	public ConflictException(String ctx) 
	{
		super(ctx);
	}
}
