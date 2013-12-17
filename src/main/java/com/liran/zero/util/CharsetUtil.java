package com.liran.zero.util;

import java.io.UnsupportedEncodingException;

public class CharsetUtil {

	private CharsetUtil() {

	}

	public enum CHARSET {
		UTF_8("utf-8"), UTF_16("utf-16"), UTF_16BE("UTF-16BE"), ISO_8859_1(
				"ISO-8859-1"), GBK("GBK"), GBK_2312("GBK2312");
		private String charset;

		private CHARSET(String charset) {
			this.charset = charset;
		}

		public String getCharset() {
			return this.charset;
		}
	}

	public static String strToCharset(String str, CHARSET charset)
			throws UnsupportedEncodingException {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("字符串为空");
		}
		return new String(str.getBytes(), charset.getCharset());
	}

}
