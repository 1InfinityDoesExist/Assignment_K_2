package com.spring.dependencyInjection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>, JpaRepository<Author, Long> {

	@Query("Select Author from #{#entityName} Author where id=?1")
	public Author getAuthorById(Long id);

	@Query("Select Author from #{#entityName} Author")
	public List<Author> getAllAuthor();

}
