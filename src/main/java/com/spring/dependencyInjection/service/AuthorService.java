package com.spring.dependencyInjection.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dependencyInjection.entity.Author;
import com.spring.dependencyInjection.exception.MyException;
import com.spring.dependencyInjection.repository.AuthorRepository;

@Service
public class AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

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
}
