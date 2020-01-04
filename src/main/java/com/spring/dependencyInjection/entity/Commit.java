package com.spring.dependencyInjection.entity;

public class Commit {
	private CommitAuthorDetails author;
	private CommitCommitter committer;
	private String message;
	private CommitTree tree;
	private String url;
	private Integer commentCount;
	private CommitVerification verification;

}
