package com.spring.dependencyInjection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Commits;

@Repository
public interface CommitRepository extends CrudRepository<Commits, Long>, JpaRepository<Commits, Long> {

}
