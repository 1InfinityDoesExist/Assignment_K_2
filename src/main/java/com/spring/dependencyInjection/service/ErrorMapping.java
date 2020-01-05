package com.spring.dependencyInjection.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMapping {

	public ResponseEntity<?> mapStateToError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
