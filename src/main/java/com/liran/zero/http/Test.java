package com.liran.zero.http;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class Test {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		HttpGet get = new HttpGet("http://news.baidu.com");
		HttpResponse response = ZeroHttpClient.httpClient.execute(get);
		HttpEntity resEntity = response.getEntity();
		System.out.println((resEntity == null) ? null : EntityUtils
				.toString(resEntity));
		resEntity.consumeContent();
		System.out.println(new Random().nextLong());
	}
}
