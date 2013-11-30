package com.liran.zero.http;

import java.util.HashMap;
import java.util.Map;

import com.liran.zero.util.StringUtil;

public class RequestParams {
	private String url;
	private Map<String, String> headers = new HashMap<String, String>();
	private String Charset = "";

	public RequestParams(String url, Map<String, String> headers, String charSet) {
		setUrl(url).setHeaders(headers).setCharset(charSet);
	}

	public RequestParams(String requestURL) {
		this(requestURL, null, null);
		this.headers.put(REQUEST_TYPE.USER_AGENT.getValue(),
				USER_AGENT.UBUNTU_CHROMIUM_30_0.getUserAgent());
	}

	public String getUrl() {
		return url;
	}

	public RequestParams setUrl(String url) {
		if (StringUtil.isEmpty(url)) {
			throw new IllegalArgumentException("不能设置网址为空或者null");
		}
		this.url = url;
		return this;
	}

	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(this.headers);
	}

	public RequestParams putHead(String paramName, String value) {
		if (StringUtil.isEmpty(paramName) || value == null) {
			throw new IllegalArgumentException("参数名 或者 参数值 为空");
		}
		this.headers.put(paramName, value);
		return this;
	}

	public RequestParams setHeaders(Map<String, String> headers) {
		if (headers != null) {
			this.headers.putAll(headers);
		}
		return this;
	}

	public String getCharset() {
		return Charset;
	}

	public RequestParams setCharset(String charset) {
		Charset = charset;
		return this;
	}

	public RequestParams setCookie(String cookie) {
		if (StringUtil.isEmpty(cookie)) {
			throw new IllegalArgumentException("Cookie 不能为空");
		}
		this.putHead(REQUEST_TYPE.COOKIE.getValue(), cookie);
		return this;
	}

}
