package com.demo.exception;

/**
 * 接口业务通用异常
 * @author dave chen
 *
 */
public class InterfaceServiceException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InterfaceServiceException(String msg)
	{
		super(msg);
	}
	
	public InterfaceServiceException(Throwable cause)
	{
		super(cause);
	}
}
