package com.liran.zero.http;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class ZeroHttpClient {

	private static final String CHARSET = HTTP.UTF_8;
	public static final DefaultHttpClient httpClient = getMutlDefaultHttpClient();

	private ZeroHttpClient() {

	}

	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getMutlDefaultHttpClient() {
		HttpParams params = new BasicHttpParams();
		// 设置一些基本参数
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//		HttpProtocolParams.setContentCharset(params, CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams.setUserAgent(params,
				USER_AGENT.UBUNTU_CHROMIUM_30_0.getUserAgent());
		// 超时设置
		// 从连接池中取连接的超时时间
		ConnManagerParams.setTimeout(params, 1000);
		//设置链接时间
		HttpConnectionParams.setConnectionTimeout(params, 2000);
		//设置超时时间
		HttpConnectionParams.setSoTimeout(params, 4000);
		//设置最大链接数目
		ConnManagerParams.setMaxTotalConnections(params, 200);
		// 设置我们的HttpClient支持HTTP和HTTPS两种模式
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		// 使用线程安全的连接管理来创建HttpClient
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);
		return new DefaultHttpClient(conMgr, params);
	}
}
