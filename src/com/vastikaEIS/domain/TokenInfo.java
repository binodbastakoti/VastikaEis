package com.vastikaEIS.domain;

import org.joda.time.DateTime;

public class TokenInfo {
	private long userId;
	private DateTime issued;
	private DateTime expires;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public DateTime getIssued() {
		return issued;
	}

	public void setIssued(DateTime issued) {
		this.issued = issued;
	}

	public DateTime getExpires() {
		return expires;
	}

	public void setExpires(DateTime expires) {
		this.expires = expires;
	}
}
