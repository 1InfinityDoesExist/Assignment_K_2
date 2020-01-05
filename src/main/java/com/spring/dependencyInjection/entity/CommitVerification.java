package com.spring.dependencyInjection.entity;

import java.io.Serializable;

public class CommitVerification implements Serializable {
	private Boolean verified;
	private String reason;
	private String signature;
	private String payload;

	public CommitVerification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommitVerification(Boolean verified, String reason, String signature, String payload) {
		super();
		this.verified = verified;
		this.reason = reason;
		this.signature = signature;
		this.payload = payload;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((verified == null) ? 0 : verified.hashCode());
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
		CommitVerification other = (CommitVerification) obj;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (verified == null) {
			if (other.verified != null)
				return false;
		} else if (!verified.equals(other.verified))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommitVerification [verified=" + verified + ", reason=" + reason + ", signature=" + signature
				+ ", payload=" + payload + "]";
	}

}
