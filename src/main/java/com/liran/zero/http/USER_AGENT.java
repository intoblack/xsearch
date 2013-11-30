package com.liran.zero.http;

public enum USER_AGENT {

	UBUNTU_CHROMIUM_30_0(
			"Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/30.0.1599.114 Chrome/30.0.1599.114 Safari/537.36"), 
	MOBILE_MOZILLA_5_0(
			"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
	private final String value;

	private USER_AGENT(String userAgent) {
		this.value = userAgent;
	}

	public String getUserAgent() {
		return this.value;
	}
}
