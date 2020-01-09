package com.spring.dependencyInjection.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.AuthorRepository;
import com.spring.dependencyInjection.util.ReflectionUtil;

@Service
public class AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Autowired
	private AuthorRepository authorRepository;

	public Author saveResourceInDB(Author author) {
		Author authorFromDB = null;
		try {
			authorFromDB = authorRepository.save(author);
			if (authorFromDB == null) {
				throw new MyException("Sorry Could Not Persist Author Resource In The Database");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return authorFromDB;

	}

	public Author getAuthorResource(Long id) {
		Author authorFromDB = null;
		try {
			authorFromDB = authorRepository.getAuthorById(id);
			if (authorFromDB == null) {
				throw new MyException("Sorry Could Not Retrieve Author Resource From DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return authorFromDB;
	}

	public List<Author> getAllAuthorResource() {
		List<Author> listOfAuthor = null;
		try {
			listOfAuthor = authorRepository.getAllAuthor();
			if (listOfAuthor.size() == 0 || listOfAuthor == null) {
				throw new MyException("Sorry Could Not Retrive All Author Resource from DB");
			}
		} catch (final MyException ex) {
			logger.info(ex.getMessage());
		}
		return listOfAuthor;
	}

	public String deleteAuthorResource(Long id) {
		String response = null;
		try {
			Author authorFromDB = getAuthorResource(id);
			if (authorFromDB == null) {
				throw new MyException("Sorry Could Not Retrieve Data From Resource");
			}
			response = "SuccessFully Deleted";
		} catch (final MyException ex) {
			logger.info(ex.getMessage());
		}
		return response;
	}

	public Author updateAuthorById(String author, Long id) throws JsonMappingException, JsonProcessingException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {

		Author authorFromDB = getAuthorResource(id);
		if (authorFromDB == null) {
			return null;
		}
		Author authorFromPayload = objectMapper.readValue(author, Author.class);
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(author);
			for (Iterator iterator = ((Map<String, String>) jsonObject).keySet().iterator(); iterator.hasNext();) {
				String propName = (String) iterator.next();
				refUtil.getSetterMethod("Author", propName).invoke(authorFromDB, jsonObject.get(propName));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Author authorToDB = authorRepository.save(authorFromDB);
		return authorToDB;

	}
}
