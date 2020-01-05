package com.spring.dependencyInjection.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dependencyInjection.entity.Commits;
import com.spring.dependencyInjection.service.ErrorMapping;

@RestController
@RequestMapping(path = "/api/object/commit")
public class CommitController {

	@Autowired
	private ErrorMapping errorMapping;

	public ResponseEntity<?> createResource(@Valid @RequestBody Commits commits, BindingResult bindingResult) {

		ResponseEntity<?> errorMap = errorMapping.mapStateToError(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}

		final String url = "https://api.github.com/repositories/19438/commits";

	}

}
