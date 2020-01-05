package com.spring.dependencyInjection.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "CommitCommitterTypes", typeClass = CommitCommitterTypes.class),
		@TypeDef(name = "CommitAuthorDetailsType", typeClass = CommitAuthorDetailsType.class),
		@TypeDef(name = "CommitTreeTypes", typeClass = CommitTreeTypes.class),
		@TypeDef(name = "CommitVerificationTypes", typeClass = CommitVerificationTypes.class) })

public class Commit {

	@Column(columnDefinition = "jsonb")
	@Type(type = "CommitAuthorDetailsType")
	private CommitAuthorDetails author;

	@Column(columnDefinition = "jsonb")
	@Type(type = "CommitCommitterTypes")
	private CommitCommitter committer;
	private String message;

	@Column(columnDefinition = "jsonb")
	@Type(type = "CommitTreeTypes")
	private CommitTree tree;
	private String url;
	private Integer commentCount;

	@Column(columnDefinition = "jsonb")
	@Type(type = "CommitVerificationTypes")
	private CommitVerification verification;

	public Commit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commit(CommitAuthorDetails author, CommitCommitter committer, String message, CommitTree tree, String url,
			Integer commentCount, CommitVerification verification) {
		super();
		this.author = author;
		this.committer = committer;
		this.message = message;
		this.tree = tree;
		this.url = url;
		this.commentCount = commentCount;
		this.verification = verification;
	}

	public CommitAuthorDetails getAuthor() {
		return author;
	}

	public void setAuthor(CommitAuthorDetails author) {
		this.author = author;
	}

	public CommitCommitter getCommitter() {
		return committer;
	}

	public void setCommitter(CommitCommitter committer) {
		this.committer = committer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommitTree getTree() {
		return tree;
	}

	public void setTree(CommitTree tree) {
		this.tree = tree;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public CommitVerification getVerification() {
		return verification;
	}

	public void setVerification(CommitVerification verification) {
		this.verification = verification;
	}

	@Override
	public String toString() {
		return "Commit [author=" + author + ", committer=" + committer + ", message=" + message + ", tree=" + tree
				+ ", url=" + url + ", commentCount=" + commentCount + ", verification=" + verification + "]";
	}

}
