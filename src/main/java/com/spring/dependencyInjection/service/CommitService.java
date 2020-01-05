package com.spring.dependencyInjection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dependencyInjection.entity.Commits;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.CommitRepository;

@Service
public class CommitService {

	private static final Logger logger = LoggerFactory.getLogger(CommitService.class);

	@Autowired
	private CommitRepository commitRepository;

	public Commits saveCommitsResource(Commits commits) {
		Commits commitToDB = null;
		try {
			commitToDB = commitRepository.save(commits);
			if (commitToDB == null) {
				throw new MyException("Sorry Could Not Persist Commit Resource In The DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return commitToDB;
	}

}
