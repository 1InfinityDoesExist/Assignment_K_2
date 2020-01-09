package com.spring.dependencyInjection.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.service.AuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/object/author")
@Api(value = "/api/object/author", description = "Api To Call Crud Operation of Author Class")
public class AuthorController {

	private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "/create", notes = "To Create Author Resource In Database", response = Author.class)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
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

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "/get", notes = "Retrieve Author Resource", response = Author.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> getAuthorById(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		Author author = authorService.getAuthorResource(id);
		if (author == null) {
			return new ResponseEntity<String>("Author Resource Could Not Be Found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	@RequestMapping(path = "/get1", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "/get1", notes = "Retrieve All Author Resource", response = Author.class, responseContainer = "LIST")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> getAllAuthor() {
		List<Author> listOfAuthor = authorService.getAllAuthorResource();
		if (listOfAuthor == null || listOfAuthor.size() == 0) {
			return new ResponseEntity<String>("Sorry Nothing To Display", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Author>>(listOfAuthor, HttpStatus.OK);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = "text/plain")
	@ApiOperation(value = "/delete", notes = "Remove Author Resource From DB", response = String.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> deleteAuthorByID(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {

		String response = authorService.deleteAuthorResource(id);
		if (response.equals("") || response == null) {
			return new ResponseEntity<String>("Sorry Could Not Remove Resource From DB", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> updateAuthorById(@RequestBody String author,
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id)
			throws JsonMappingException, JsonProcessingException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {

		Author authorFromDB = authorService.updateAuthorById(author, id);
		if (authorFromDB == null) {
			return new ResponseEntity<String>("Sorry Coudl Not Update The author resource", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Author>(authorFromDB, HttpStatus.OK);

	}

}
