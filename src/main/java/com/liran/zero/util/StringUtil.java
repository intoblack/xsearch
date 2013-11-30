package com.liran.zero.util;

public class StringUtil {
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty() ? true : false;
	}

}
