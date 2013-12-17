package com.liran.zero.http;

/**
 * 网络请求返回体
 * 
 * @author lixuze
 * 
 */
public class ZeroResponse {

	private String content; // 任何网络返回格式
	private String url; // 任何网络请求地址

	public ZeroResponse(String content, String url) {
		this.content = content;
		this.url = url;
	}

	public ZeroResponse() {

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
