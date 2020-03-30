package com.github.com.jorgdz.app.exceptions;

public class ForbiddenException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3738009364322486603L;

	public ForbiddenException(String context) 
	{
		super(context);
	}
}
