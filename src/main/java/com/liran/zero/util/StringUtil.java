package com.liran.zero.util;

public class StringUtil {

	private StringUtil() {
	}

	/**
	 * 
	 * 字符串是否为空
	 * 
	 * @param str
	 *            检测的字符串
	 * @return 空 true 有长度 true
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty() ? true : false;
	}

	/**
	 * 查看是否为一个字符串
	 * 
	 * @param str 字符串
	 * @return 返回是否为空字符串
	 */
	public static boolean isBlank(CharSequence str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将一个实现Iterable 类 转换成为字符串
	 * 
	 * @param wordList
	 *            转换的类
	 * @param splitWord
	 *            分隔符
	 * @return 返回一个字符串
	 */
	public static <L extends Iterable<T>, T> String wordListToString(
			L wordList, String splitWord) {
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

	/**
	 * 
	 * 清除不必要字符 例如 。，等字符
	 * 
	 * 算法可以这样实现 先将字符串 ignore 排序 之后取得的字符串 二分法搜索 这样提高搜索速度
	 * 
	 * @param words
	 *            要去除的字符串
	 * @param ignore
	 *            去除的符号等
	 * @return
	 */
	public static String clearWord(String words, char[] ignore) {
		if (words == null || words.length() == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < words.length(); i++) {
			boolean notInsert = false;
			for (char seg : ignore) {
				if (words.charAt(i) == seg) {
					notInsert = true;
				}
			}
			if (!notInsert) {
				buffer.append(words.charAt(i));
			}
		}
		return buffer.toString();
	}

}
