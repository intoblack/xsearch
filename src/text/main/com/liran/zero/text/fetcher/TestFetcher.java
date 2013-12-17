package com.liran.zero.text.fetcher;

import com.liran.zero.exception.ZeroFetcherException;
import com.liran.zero.fetcher.WebFetcher;
import com.liran.zero.http.RequestParams;

public class TestFetcher {
	
	public static void main(String[] args) throws ZeroFetcherException {
		WebFetcher fetcher = new WebFetcher();
		System.out.println(fetcher.getHtml(new RequestParams(
				"http://s.weibo.com/weibo/%25E5%25BC%2580%25E5%25BF%2583&Refer=index")));
	}

}
