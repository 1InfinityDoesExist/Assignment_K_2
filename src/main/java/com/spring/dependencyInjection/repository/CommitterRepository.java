package com.spring.dependencyInjection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Committer;

@Repository
public interface CommitterRepository extends JpaRepository<Committer, Long>, CrudRepository<Committer, Long> {

}
