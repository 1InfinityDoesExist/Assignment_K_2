package com.spring.dependencyInjection.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
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
import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.service.CommitterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/object")
@Api(value = "/api/object", description = "Commiiter Crud Api Operation")
public class CommitterController {

	final private static Logger logger = LoggerFactory.getLogger(CommitterController.class);

	@Autowired
	private CommitterService committerService;

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "/create", notes = "Create Resource", response = Committer.class)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<?> createResource(@Valid @RequestBody Committer committer, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> error = new HashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.CREATED);
		}

		Committer committerFromDB = committerService.createCommitterResource(committer);
		return new ResponseEntity<Committer>(committerFromDB, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, consumes = "application/json", produces = "applicaiton/json")
	@ApiOperation(value = "/create", notes = "Get Resource", produces = "application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getCommitter(@RequestParam(value = "id", required = true) Long id) {

		Committer committer = committerService.getCommitterById(id);
		if (committer == null) {
			return new ResponseEntity<String>("Sorry No Value Returned For Thi Committer Id" + id, HttpStatus.OK);
		}
		return new ResponseEntity<Committer>(committer, HttpStatus.OK);
	}

	@RequestMapping(path = "getAll", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "/getAll", notes = "Get Resource", response = Committer.class, responseContainer = "LIST")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getCommiiter() {
		List<Committer> listOfCommitter = committerService.getCommitter();
		if (listOfCommitter == null || listOfCommitter.size() == 0) {
			return new ResponseEntity<String>("Sorry No Value Returned For This Committer", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Committer>>(listOfCommitter, HttpStatus.OK);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = "applicaition/json")
	@ApiOperation(value = "/delete", notes = "Commiter ")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteCommitterById(@RequestParam(value = "id", required = true) Long id) {
		String response = committerService.deleteCommitterByID(id);
		if (response == null) {
			return new ResponseEntity<String>("Sorry Could Not Delete Committer By ID", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PATCH, produces = "application/json")
	@ApiOperation(value = "/update", notes = "Update Committer By ID")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateCommiterByID(@RequestBody String committer,
			@RequestParam(value = "id", required = true) Long id) throws JsonMappingException, JsonProcessingException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException, ParseException {

		Committer committerFromDB = committerService.updateCommitterByID(committer, id);
		if (committerFromDB != null) {
			return new ResponseEntity<String>("Could Not Update Committer By ID", HttpStatus.OK);
		}
		return new ResponseEntity<Committer>(committerFromDB, HttpStatus.OK);

	}

}
