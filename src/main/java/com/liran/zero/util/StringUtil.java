package com.liran.zero.util;

public class StringUtil {
	
	
	/**
	 * 
	 * 字符串是否为空
	 * 
	 * @param str 检测的字符串
	 * @return 空 true 有长度 true
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty() ? true : false;
	}
	
	
	
	/**
	 * 将一个实现Iterable 类 转换成为字符串
	 * 
	 * @param wordList 转换的类
	 * @param splitWord 分隔符
	 * @return 返回一个字符串
	 */
	public static <L extends Iterable<T>, T> String wordListToString(L wordList, String splitWord) {
		StringBuffer stringBuffer = new StringBuffer();
		boolean isFirst = true;
		for (T word : wordList) {
			if (isFirst) {
				stringBuffer.append(word);
				isFirst = false;
			} else {
				stringBuffer.append(splitWord + word);
			}
		}
		return stringBuffer.toString();
	}

}
