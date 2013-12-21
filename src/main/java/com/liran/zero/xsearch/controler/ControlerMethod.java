package com.liran.zero.xsearch.controler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 类似观察者模式的接口
 * 
 * 
 * @author lixuze
 *
 * @param <T> 实现接口的类
 * @param <E> 接口返回类
 * @param <U> 接口参数
 */
public abstract class ControlerMethod<T, E,U> {

	private static final Logger logger = Logger.getLogger(ControlerMethod.class);
	// 存储搜索接口的map
	protected Map<String, T> methoMap = new LinkedHashMap<String, T>();

	/**
	 * 添加使用的接口
	 * 
	 * @param bookSearch
	 *            搜索接口
	 * @return true 添加 false 接口为空 或者 已经存在这个接口
	 */
	public synchronized boolean registerMethod(T method) {
		if (method == null
				|| methoMap.containsKey(method.getClass().getName())) {
			logger.info("添加接口类失败 : " + method);
			return false;
		}
		logger.info("添加书籍搜索 : " + method.getClass().getName());
		this.methoMap.put(method.getClass().getName(), method);
		return true;
	}

	/**
	 * 移除要使用的接口
	 * 
	 * @param bookSearch
	 *            搜索接口
	 * @return
	 */
	public synchronized boolean removeMethod(T method) {
		if (method == null || !methoMap.containsKey(method.getClass().getName())) {
			return false;
		}
		logger.info("添加接口名 : " + method.getClass().getName());
		this.methoMap.remove(method.getClass().getName());
		return true;
	}

	public abstract E getMethodResult(U seed);

}
