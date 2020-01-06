package com.spring.dependencyInjection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.dependencyInjection.entity.Committer;

@Repository
public interface CommitterRepository extends JpaRepository<Committer, Long>, CrudRepository<Committer, Long> {

	@Query("Select Committer from #{#entityName} Committer where id=?1")
	public Committer getCommitterByID(Long id);

	@Query("Select Committer from #{#entityName} Committer ")
	public List<Committer> getAllCommitter();

}
