package com.studio.elephant.web.framework.register;

import com.studio.elephant.web.framework.handler.throwable.IExceptionHandler;
/**
 * 全局注册器
 * @author file
 * @since 2015-3-28 下午3:01:35
 * @version 1.0
 */
public final class GlobalRegister {
	
	//handler异常处理器
	private static IExceptionHandler  _exceptionHandler;
	/**
	 * 设置异常处理器	
	 * @param exceptionHandler
	 */
	public static void setExceptionHandler(IExceptionHandler exceptionHandler){
		_exceptionHandler = exceptionHandler;
	}
	/**
	 * 获取异常处理器
	 * @return
	 */
	public static IExceptionHandler getExceptionHandler(){
		return _exceptionHandler;
	}
	

}
