package com.github.com.jorgdz.app.exceptions;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class HandlerException {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
    	NoHandlerFoundException.class,
    	NotFoundException.class
    })
    public Error notFoundException(HttpServletRequest request, Exception exception) {
		if(exception instanceof NoHandlerFoundException)
		{
			return new Error("No encontrado !!", exception, request.getRequestURI());
		}
		
        return new Error(exception, request.getRequestURI());
    }
	
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
    		BadRequestException.class,
            //org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class
    })
    public Error badRequestException(HttpServletRequest request, Exception exception) {
		if(exception instanceof HttpRequestMethodNotSupportedException)
	    {
	     	return new Error("No se admite peticiones de tipo " + ((HttpRequestMethodNotSupportedException) exception).getMethod() + ", solo se admite solicitudes de tipo " + Arrays.toString(((HttpRequestMethodNotSupportedException) exception).getSupportedMethods()), 
	     			exception, request.getRequestURI());
	    }
    	
		if(exception instanceof MissingRequestHeaderException)
        {
			return new Error("El valor de la cabecera " + ((MissingRequestHeaderException) exception).getHeaderName() + " no es válido.", 
	     			exception, request.getRequestURI());
        }
		
		if(exception instanceof MissingServletRequestParameterException)
	    {
			return new Error("El parámetro " + ((MissingServletRequestParameterException) exception).getParameterName() + " es requerido.", 
	     			exception, request.getRequestURI());
	    }        
	        
    	if (exception instanceof MethodArgumentTypeMismatchException) 
        {
        	return new Error("No se puede convertir el valor " + ((MethodArgumentTypeMismatchException) exception).getValue() 
        			+ " a un tipo de dato " + ((MethodArgumentTypeMismatchException) exception).getRequiredType().getSimpleName(), 
        			exception, request.getRequestURI());
		}
        
    	return new Error(exception, request.getRequestURI());
    }
    
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
    	ConflictException.class
    })
    public Error conflictException(HttpServletRequest request, Exception exception) 
    {
        return new Error(exception, request.getRequestURI());
    }
    
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
    	ForbiddenException.class
    })
    public Error forbiddenException (HttpServletRequest request, Exception exception)
    {
    	return new Error(exception, request.getRequestURI());
    }
    
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
    	ArithmeticException.class,
    	Exception.class,
    })  
    public Error unexpectedException(HttpServletRequest request, Exception exception) {
        if(exception instanceof ArithmeticException)
        {
        	return new Error("Error al realizar la operación matemática", exception, request.getRequestURI());
        }
        
    	return new Error(exception, request.getRequestURI());
    }
    
}
