package com.spring.dependencyInjection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>, JpaRepository<Author, Long> {

}
