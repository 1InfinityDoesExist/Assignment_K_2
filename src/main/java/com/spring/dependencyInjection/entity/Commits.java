package com.spring.dependencyInjection.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amazonaws.services.codecommit.model.Commit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Commits")
@Table(name = "main_commit_table")
@ApiModel(value = "Commit Class", description = "This is the main class")
public class Commits implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key Of Commit Class")
	private Long id;

	private String sha;
	private String nodeId;
	private Commit commit;
	private String url;
	private String htmlUrl;
	private String commentsUrl;
	private Author author;
	private Committer committer;
	private List<Parents> parents;

}
