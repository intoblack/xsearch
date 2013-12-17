package com.liran.zero.xsearch.exception;

public class ZeroException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public ZeroException(String msg)
	{
		super(msg);
	}
	
	
	public ZeroException(Throwable cause)
	{
		super(cause);
	}
	
	public ZeroException(String msg , Throwable cause)
	{
		super(msg , cause);
	}
	
	
	public ZeroException()
	{
		super();
	}
	

}
