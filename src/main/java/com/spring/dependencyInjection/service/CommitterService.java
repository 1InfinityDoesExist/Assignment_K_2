package com.spring.dependencyInjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dependencyInjection.entity.Committer;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.CommitterRepository;

@Service
public class CommitterService {

	private static final Logger logger = LoggerFactory.getLogger(CommitterService.class);

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
}
