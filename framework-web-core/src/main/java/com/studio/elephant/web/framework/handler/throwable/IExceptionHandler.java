package com.studio.elephant.web.framework.handler.throwable;

import com.studio.elephant.web.framework.handler.IHandler;
/**
 * Ihander出现异常处理接口（具体处理方式由使用者决定，一般全局只需一个）
 * @author file
 * @since 2015-3-28 下午2:47:59
 * @version 1.0
 */
public interface IExceptionHandler extends IHandler {
	/**
	 * Ihander出现异常处理	
	 * @param throwable 异常
	 * @param handler 出现异常的IHander
	 */
	public void exceptionCaught(Throwable throwable,IHandler handler);

}
