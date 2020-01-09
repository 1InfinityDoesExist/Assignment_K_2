package com.spring.dependencyInjection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Commits;

@Repository
public interface CommitRepository extends CrudRepository<Commits, Long>, JpaRepository<Commits, Long> {

	@Query("Select Commits From #{#entityName} Commits where id=?1")
	public Commits getAuthorResourceByID(Long id);

	@Query("Select Commits from #{#entityName} Commits")
	public List<Commits> getAllAuthorResource();

	
	public void deleteCommitsById(Long id);

}
