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
import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.CommitterRepository;
import com.spring.dependencyInjection.util.ReflectionUtil;

@Service
public class CommitterService {

	private static final Logger logger = LoggerFactory.getLogger(CommitterService.class);

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Autowired
	private CommitterRepository committerRespository;

	public Committer createCommitterResource(Committer committer) {

		Committer committerToDB = null;
		try {
			committerToDB = committerRespository.save(committer);
			if (committerToDB == null) {
				throw new MyException("Sorry Could Not Persist Committer Resource To DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return committerToDB;
	}

	public Committer getCommitterById(Long id) {
		Committer committerValueByID = null;
		try {
			committerValueByID = committerRespository.getCommitterByID(id);
			if (committerValueByID == null) {
				throw new MyException("Sorry Could Not Retrieve Instance of Committer");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return committerValueByID;

	}

	public List<Committer> getCommitter() {
		List<Committer> listOfCommitter = null;
		try {
			listOfCommitter = committerRespository.getAllCommitter();
			if (listOfCommitter.size() == 0 || listOfCommitter == null) {
				throw new MyException("Sorry Could Not Retrieve Instance of Committer");
			}
		} catch (final MyException ex) {
			logger.info(ex.getMessage());
		}
		return listOfCommitter;
	}

	public String deleteCommitterByID(Long id) {
		String response = null;
		try {

			Committer authorFromDB = getCommitterById(id);
			committerRespository.deleteById(id);
			response = "SuccessFully Deleted";
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return response;
	}

	public Committer updateCommitterByID(String committer, Long id)
			throws JsonMappingException, JsonProcessingException, ParseException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, JSONException {

		Committer committerResponse = null;
		Committer committerFromDB = getCommitterById(id);
		if (committerFromDB == null) {
			return null;
		}

		Committer committerFromPayload = objectMapper.readValue(committer, Committer.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(committer);
		for (Iterator iterator = ((Map<String, String>) jsonObject).keySet().iterator(); iterator.hasNext();) {
			String propName = (String) iterator.next();

			refUtil.getSetterMethod("Committer", propName).invoke(committerFromDB, jsonObject.get(propName));
		}

		Committer committerToDB = committerRespository.save(committerFromDB);
		return committerToDB;

	}
}
