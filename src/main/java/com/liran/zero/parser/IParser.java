package com.liran.zero.parser;

import com.liran.zero.http.ZeroResponse;

public interface IParser<T> {
	
	
	/**
	 * 解析网页结构接口
	 * 
	 * @param html //网络返回内容
	 * @return
	 */
	public T parser(String html);
	
	/**
	 * 返回能否解析这个网络请求
	 * 
	 * @param response //网络请求返回
	 * @return
	 */
	public boolean assertResponse(ZeroResponse response);

}
