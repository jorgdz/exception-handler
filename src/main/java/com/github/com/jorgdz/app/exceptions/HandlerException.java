package com.github.com.jorgdz.app.exceptions;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class HandlerException {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
    	NoHandlerFoundException.class,
    	NotFoundException.class
    })
    public @ResponseBody Error notFoundRequest(HttpServletRequest request, Exception exception) {
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
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    public @ResponseBody Error badRequest(HttpServletRequest request, Exception exception) {
        if (exception instanceof MethodArgumentTypeMismatchException) 
        {
        	return new Error("No se puede convertir el valor " + ((MethodArgumentTypeMismatchException) exception).getValue() 
        			+ " a un tipo de dato " + ((MethodArgumentTypeMismatchException) exception).getRequiredType().getSimpleName(), 
        			exception, request.getRequestURI());
		}
        
        if(exception instanceof HttpRequestMethodNotSupportedException)
        {
        	return new Error("No se admite peticiones de tipo " + ((HttpRequestMethodNotSupportedException) exception).getMethod() + ", solo se admite solicitudes de tipo " + Arrays.toString(((HttpRequestMethodNotSupportedException) exception).getSupportedMethods()), 
        			exception, request.getRequestURI());
        }
     
    	return new Error(exception, request.getRequestURI());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
    	ArithmeticException.class,
    	Exception.class,
    })  
    public @ResponseBody Error fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        if(exception instanceof ArithmeticException)
        {
        	return new Error("Error al realizar la operación matemática", exception, request.getRequestURI());
        }
        
    	return new Error(exception, request.getRequestURI());
    }
    
}
