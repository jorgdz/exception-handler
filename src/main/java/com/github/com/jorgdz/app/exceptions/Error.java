package com.github.com.jorgdz.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Error {
	
	private String message;
	
	private String exception;
	
	private String uri;
	
	public Error (Exception ex, String uri)
	{
		this.message = ex.getMessage();
		this.exception = ex.getClass().getSimpleName();
		this.uri = uri;
	}
	
	public Error (String message, Exception ex, String uri)
	{
		this.message = message;
		this.exception = ex.getClass().getSimpleName();
		this.uri = uri;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Error [message=" + message + ", exception=" + exception + ", uri=" + uri + "]";
	}
	
}
