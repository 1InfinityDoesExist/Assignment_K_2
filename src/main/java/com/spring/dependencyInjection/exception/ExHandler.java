package com.spring.dependencyInjection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> myExceptionHandler(MyException ex, WebRequest webRequest) {
		MyExceptionResponse response = new MyExceptionResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
}
