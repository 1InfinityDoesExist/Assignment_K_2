package com.spring.dependencyInjection.entity;

import java.io.Serializable;

public class Parents implements Serializable {

	private String sha;
	private String url;
	private String htmlUrl;

	public Parents() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parents(String sha, String url, String htmlUrl) {
		super();
		this.sha = sha;
		this.url = url;
		this.htmlUrl = htmlUrl;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((htmlUrl == null) ? 0 : htmlUrl.hashCode());
		result = prime * result + ((sha == null) ? 0 : sha.hashCode());
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
		Parents other = (Parents) obj;
		if (htmlUrl == null) {
			if (other.htmlUrl != null)
				return false;
		} else if (!htmlUrl.equals(other.htmlUrl))
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
		return "Parents [sha=" + sha + ", url=" + url + ", htmlUrl=" + htmlUrl + "]";
	}

}
