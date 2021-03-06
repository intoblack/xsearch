package com.liran.zero.fetcher;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.liran.zero.http.RequestParams;
import com.liran.zero.http.ZeroHttpClient;
import com.liran.zero.http.ZeroResponse;
import com.liran.zero.util.StringUtil;
import com.liran.zero.xsearch.exception.ZeroException;

public class WebFetcher {
	private DefaultHttpClient httpClient = ZeroHttpClient.httpClient;
	private static final Pattern p_charset = Pattern
			.compile("charset\\s?=\\s?([a-zA-Z0-9\\-]+)");
	private static final Pattern p_encoding = Pattern
			.compile("encoding\\s?=\\s?\"([a-zA-Z0-9\\-]+)\"");

	public ZeroResponse getHtml(RequestParams requestParams)
			throws ZeroException {
		HttpResponse response = null;
		String html = null;
		try {
			HttpGet get = new HttpGet(requestParams.getUrl());
			if (requestParams.getHeaders().size() > 0) {
				for (Entry<String, String> entry : requestParams.getHeaders()
						.entrySet()) {
					get.addHeader(entry.getKey(), entry.getValue());
				}
			}
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			@SuppressWarnings("deprecation")
			String charset = StringUtil.isEmpty(requestParams.getCharset()) ? EntityUtils
					.getContentCharSet(entity) : requestParams.getCharset();
			byte[] bytes = EntityUtils.toByteArray(entity);
			html = new String(bytes);
			if (charset == null) {
				Matcher matcher = p_charset.matcher(html);
				if (matcher.find()) {
					charset = charset(matcher);
				} else {
					matcher = p_encoding.matcher(html);
					if (matcher.find()) {
						charset = charset(matcher);
					}
				}
			}
			charset = charset == null ? "utf-8" : charset;
			html = new String(bytes, charset);
		} catch (Exception e) {
			throw new ZeroException(e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					throw new ZeroException(e);
				}
			}
		}
		return new ZeroResponse(html, requestParams.getUrl());

	}

	private static String charset(Matcher matcher) {
		String charset = matcher.group(1).trim();
		if ("GB2312".equalsIgnoreCase(charset)) {
			charset = "GBK";
		} else if (charset.toUpperCase().contains("UTF")) {
			charset = "UTF-8";
		}
		return charset;
	}

	public void setProxy(HttpHost proxy) {
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxy);
	}

}
