package com.liran.zero.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static final long SEC = 1000;
	public static final long MINUTE = SEC * 60;
	public static final long HOUR = MINUTE * 60;
	public static final long DAY = HOUR * 24;

	private DateUtil() {

	}

	/**
	 * 获得时间字符串的毫秒数
	 * 
	 * @param timeString 时间字符串  2012-02-03
	 * @return 返回时间
	 * @throws ParseException
	 */
	public static long timeStringToTime(String timeString)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(timeString).getTime();
	}

	
	/**
	 * 
	 * 取得当前日期 毫秒数     
	 * 
	 * @return
	 */
	public static long getNowDate() {
		return System.currentTimeMillis() / DAY * DAY;
	}
	
	
	
	
	public static void main(String args[]) throws ParseException {
		System.out.println(timeStringToTime("2013-12-01"));
	}
}
