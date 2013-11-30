package com.liran.zero.http;

public enum REQUEST_TYPE {
	USER_AGENT("User-Agent"), REFERER("Referer"), COOKIE("Cookie"), HOST("Host");

	private final String value;

	private REQUEST_TYPE(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
