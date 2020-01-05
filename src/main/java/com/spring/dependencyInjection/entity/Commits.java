package com.spring.dependencyInjection.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amazonaws.services.codecommit.model.Commit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Commits")
@Table(name = "main_commit_table")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "CommitType", typeClass = CommitType.class),
		@TypeDef(name = "ParentsTypes", typeClass = ParentsTypes.class) })
@ApiModel(value = "Commit Class", description = "This is the main class")
public class Commits implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key Of Commit Class")
	private Long id;

	@Column(name = "sha")
	@ApiModelProperty(notes = "sha")
	private String sha;

	@Column(name = "node_id")
	@ApiModelProperty(notes = "Commits Property:- nodeId")
	private String nodeId;

	@Column(name = "commit", columnDefinition = "jsonb")
	@Type(type = "CommitType")
	@ApiModelProperty(notes = "Commits Property:- commit")
	private Commit commit;

	@Column(name = "url")
	@ApiModelProperty(notes = "Commits Property :url")
	private String url;

	@Column(name = "html_url")
	@ApiModelProperty(notes = "Commits Property:- html_url")
	private String htmlUrl;

	@Column(name = "comments_url")
	@ApiModelProperty(notes = "Commits Property :-comments_url")
	private String commentsUrl;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "author_id", columnDefinition = "bigint", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties("commits")
	@ApiModelProperty(name = "Commts Property :- author")
	private Author author_id;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "committer_id", columnDefinition = "bigint", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties("commit_id")
	@ApiModelProperty(notes = "Commits Property :- committer")
	private Committer committer_id;

	@Column(name = "parents", columnDefinition = "jsonb")
	@Type(type = "com.spring.dependencyInjection.entity.ParentsTypes", parameters = {
			@org.hibernate.annotations.Parameter(name = "type", value = "LIST"),
			@org.hibernate.annotations.Parameter(name = "element", value = "com.spring.dependencyInjection.entity.Parents") })
	@ApiModelProperty(notes = "Commits Property :- Parents")
	private List<Parents> parents = new ArrayList<Parents>();

	public Commits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commits(Long id, String sha, String nodeId, Commit commit, String url, String htmlUrl, String commentsUrl,
			Author author, Committer committer, List<Parents> parents) {
		super();
		this.id = id;
		this.sha = sha;
		this.nodeId = nodeId;
		this.commit = commit;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.commentsUrl = commentsUrl;

		this.parents = parents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentsUrl == null) ? 0 : commentsUrl.hashCode());
		result = prime * result + ((commit == null) ? 0 : commit.hashCode());

		result = prime * result + ((htmlUrl == null) ? 0 : htmlUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
		result = prime * result + ((sha == null) ? 0 : sha.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getCommentsUrl() {
		return commentsUrl;
	}

	public void setCommentsUrl(String commentsUrl) {
		this.commentsUrl = commentsUrl;
	}

	public List<Parents> getParents() {
		return parents;
	}

	public void setParents(List<Parents> parents) {
		this.parents = parents;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commits other = (Commits) obj;

		if (commentsUrl == null) {
			if (other.commentsUrl != null)
				return false;
		} else if (!commentsUrl.equals(other.commentsUrl))
			return false;
		if (commit == null) {
			if (other.commit != null)
				return false;
		} else if (!commit.equals(other.commit))
			return false;

		if (htmlUrl == null) {
			if (other.htmlUrl != null)
				return false;
		} else if (!htmlUrl.equals(other.htmlUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		if (parents == null) {
			if (other.parents != null)
				return false;
		} else if (!parents.equals(other.parents))
			return false;
		if (sha == null) {
			if (other.sha != null)
				return false;
		} else if (!sha.equals(other.sha))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Commits [id=" + id + ", sha=" + sha + ", nodeId=" + nodeId + ", commit=" + commit + ", url=" + url
				+ ", htmlUrl=" + htmlUrl + ", commentsUrl=" + commentsUrl + ", parents=" + parents + "]";
	}

}
