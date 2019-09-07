package com.training.boothibernate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.training.boothibernate.entity.CustomerErrorResponse;
// @RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerException ex){
		CustomerErrorResponse errResponse=new CustomerErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												ex.getMessage(),
												System.currentTimeMillis());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		CustomerErrorResponse errResponse=new CustomerErrorResponse(
												HttpStatus.NOT_FOUND.value(),
												ex.getMessage(),
												System.currentTimeMillis());
		return new ResponseEntity<>(errResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
		CustomerErrorResponse errResponse=new CustomerErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												ex.getMessage(),
												System.currentTimeMillis());
		return new ResponseEntity<>(errResponse,HttpStatus.BAD_REQUEST);
	}
}
