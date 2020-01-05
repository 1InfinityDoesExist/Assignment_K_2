package com.spring.dependencyInjection.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Committer")
@Table(name = "committer")
@ApiModel(value = "Committer Class", description = "Committer Class Properties")
public class Committer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Committer Property :- login")
	private Long id;

	@Column(name = "login")
	@ApiModelProperty(notes = "Committer Property :- login")
	private String login;

	@Column(name = "node_id")
	@ApiModelProperty(notes = "Committer Property :- node_id")
	private String nodeId;

	@Column(name = "avatar_url")
	@ApiModelProperty(notes = "Committer Property :- avatar_url")
	private String avatorUrl;

	@Column(name = "gravatar_id")
	@ApiModelProperty(notes = "Committer Property :- gravatar_id")
	private String gravatarId;

	@Column(name = "url")
	@ApiModelProperty(notes = "Committer Property :- url")
	private String url;

	@Column(name = "html_url")
	@ApiModelProperty(notes = "Committer Property :- html_url")
	private String htmlUrl;

	@Column(name = "followers_url")
	@ApiModelProperty(notes = "Committer Property :- followers_url")
	private String followersUrl;

	@Column(name = "following_url")
	@ApiModelProperty(notes = "Committer Property :- following_url")
	private String followingUrl;

	@Column(name = "gists_url")
	@ApiModelProperty(notes = "Committer Property :- gists_url")
	private String gistsUrl;

	@Column(name = "starred_url")
	@ApiModelProperty(notes = "Committer Property :- starred_url")
	private String starredUrl;

	@Column(name = "subscriptions_url")
	@ApiModelProperty(notes = "Committer Property :- subscriptions_url")
	private String subscriptionsUrl;

	@Column(name = "organizations_url")
	@ApiModelProperty(notes = "Committer Property :- organizations_url")
	private String organizationsUrl;

	@Column(name = "repos_url")
	@ApiModelProperty(notes = "Committer Property :- repos_url")
	private String reposUrl;

	@Column(name = "events_url")
	@ApiModelProperty(notes = "Committer Property :- events_url")
	private String eventsUrl;

	@Column(name = "received_events_url")
	@ApiModelProperty(notes = "Committer Property :- received_evetns_url")
	private String receivedEventsUrl;

	@Column(name = "type")
	@ApiModelProperty(notes = "Committer Property :- type")
	private String type;

	@Column(name = "site_admin")
	@ApiModelProperty(notes = "Committer Property :- site_admin")
	private Boolean siteAdmin;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "committer_id")
	@JsonIgnoreProperties("committer_id")
	private Commits commit_id;

	public Commits getCommit_id() {
		return commit_id;
	}

	public void setCommit_id(Commits commit_id) {
		this.commit_id = commit_id;
	}

	public Committer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Committer(Long id, String login, String nodeId, String avatorUrl, String gravatarId, String url,
			String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
			String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
			String receivedEventsUrl, String type, Boolean siteAdmin) {
		super();
		this.id = id;
		this.login = login;
		this.nodeId = nodeId;
		this.avatorUrl = avatorUrl;
		this.gravatarId = gravatarId;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.followersUrl = followersUrl;
		this.followingUrl = followingUrl;
		this.gistsUrl = gistsUrl;
		this.starredUrl = starredUrl;
		this.subscriptionsUrl = subscriptionsUrl;
		this.organizationsUrl = organizationsUrl;
		this.reposUrl = reposUrl;
		this.eventsUrl = eventsUrl;
		this.receivedEventsUrl = receivedEventsUrl;
		this.type = type;
		this.siteAdmin = siteAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getAvatorUrl() {
		return avatorUrl;
	}

	public void setAvatorUrl(String avatorUrl) {
		this.avatorUrl = avatorUrl;
	}

	public String getGravatarId() {
		return gravatarId;
	}

	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
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

	public String getFollowersUrl() {
		return followersUrl;
	}

	public void setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
	}

	public String getFollowingUrl() {
		return followingUrl;
	}

	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	public String getGistsUrl() {
		return gistsUrl;
	}

	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	public String getStarredUrl() {
		return starredUrl;
	}

	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	public String getReposUrl() {
		return reposUrl;
	}

	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	public void setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(Boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatorUrl == null) ? 0 : avatorUrl.hashCode());
		result = prime * result + ((eventsUrl == null) ? 0 : eventsUrl.hashCode());
		result = prime * result + ((followersUrl == null) ? 0 : followersUrl.hashCode());
		result = prime * result + ((followingUrl == null) ? 0 : followingUrl.hashCode());
		result = prime * result + ((gistsUrl == null) ? 0 : gistsUrl.hashCode());
		result = prime * result + ((gravatarId == null) ? 0 : gravatarId.hashCode());
		result = prime * result + ((htmlUrl == null) ? 0 : htmlUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + ((organizationsUrl == null) ? 0 : organizationsUrl.hashCode());
		result = prime * result + ((receivedEventsUrl == null) ? 0 : receivedEventsUrl.hashCode());
		result = prime * result + ((reposUrl == null) ? 0 : reposUrl.hashCode());
		result = prime * result + ((siteAdmin == null) ? 0 : siteAdmin.hashCode());
		result = prime * result + ((starredUrl == null) ? 0 : starredUrl.hashCode());
		result = prime * result + ((subscriptionsUrl == null) ? 0 : subscriptionsUrl.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Committer other = (Committer) obj;
		if (avatorUrl == null) {
			if (other.avatorUrl != null)
				return false;
		} else if (!avatorUrl.equals(other.avatorUrl))
			return false;
		if (eventsUrl == null) {
			if (other.eventsUrl != null)
				return false;
		} else if (!eventsUrl.equals(other.eventsUrl))
			return false;
		if (followersUrl == null) {
			if (other.followersUrl != null)
				return false;
		} else if (!followersUrl.equals(other.followersUrl))
			return false;
		if (followingUrl == null) {
			if (other.followingUrl != null)
				return false;
		} else if (!followingUrl.equals(other.followingUrl))
			return false;
		if (gistsUrl == null) {
			if (other.gistsUrl != null)
				return false;
		} else if (!gistsUrl.equals(other.gistsUrl))
			return false;
		if (gravatarId == null) {
			if (other.gravatarId != null)
				return false;
		} else if (!gravatarId.equals(other.gravatarId))
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		if (organizationsUrl == null) {
			if (other.organizationsUrl != null)
				return false;
		} else if (!organizationsUrl.equals(other.organizationsUrl))
			return false;
		if (receivedEventsUrl == null) {
			if (other.receivedEventsUrl != null)
				return false;
		} else if (!receivedEventsUrl.equals(other.receivedEventsUrl))
			return false;
		if (reposUrl == null) {
			if (other.reposUrl != null)
				return false;
		} else if (!reposUrl.equals(other.reposUrl))
			return false;
		if (siteAdmin == null) {
			if (other.siteAdmin != null)
				return false;
		} else if (!siteAdmin.equals(other.siteAdmin))
			return false;
		if (starredUrl == null) {
			if (other.starredUrl != null)
				return false;
		} else if (!starredUrl.equals(other.starredUrl))
			return false;
		if (subscriptionsUrl == null) {
			if (other.subscriptionsUrl != null)
				return false;
		} else if (!subscriptionsUrl.equals(other.subscriptionsUrl))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "Committer [id=" + id + ", login=" + login + ", nodeId=" + nodeId + ", avatorUrl=" + avatorUrl
				+ ", gravatarId=" + gravatarId + ", url=" + url + ", htmlUrl=" + htmlUrl + ", followersUrl="
				+ followersUrl + ", followingUrl=" + followingUrl + ", gistsUrl=" + gistsUrl + ", starredUrl="
				+ starredUrl + ", subscriptionsUrl=" + subscriptionsUrl + ", organizationsUrl=" + organizationsUrl
				+ ", reposUrl=" + reposUrl + ", eventsUrl=" + eventsUrl + ", receivedEventsUrl=" + receivedEventsUrl
				+ ", type=" + type + ", siteAdmin=" + siteAdmin + "]";
	}

}
