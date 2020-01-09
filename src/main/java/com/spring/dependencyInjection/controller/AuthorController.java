package com.spring.dependencyInjection.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.service.AuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/object/author")
@Api(value = "/api/object/author", description = "Api To Call Crud Operation of Author Class")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "/create", notes = "To Create Author Resource In Database", response = Author.class)
	public ResponseEntity<?> createInstance(@Valid @RequestBody Author author, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}
		Author authorResponse = authorService.saveResourceInDB(author);
		return new ResponseEntity<Author>(authorResponse, HttpStatus.CREATED);

	}

}
