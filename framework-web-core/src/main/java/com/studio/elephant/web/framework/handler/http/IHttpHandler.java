package com.studio.elephant.web.framework.handler.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studio.elephant.web.framework.handler.IHandler;
import com.studio.elephant.web.framework.handler.IResult;
/**
 * http请求处理handler接口<br/>
 * <p><b>一般情况只处理Get方法和Post方法</b></p>
 * @author file
 * @since 2015-3-28 上午11:22:38
 * @version 1.0
 */
public interface IHttpHandler extends IHandler {
	/**
	 * 处理Get请求
	 * @param req  请求对象
	 * @param resp 响应对象
	 * @return 处理结果
	 * @throws Exception 抛出异常
	 */
	public IResult doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception ;
	
	/**
	 * 处理Post请求
	 * @param req  请求对象
	 * @param resp  响应对象 
	 * @return  处理结果
	 * @throws Exception 抛出异常
	 */
	public IResult doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception ;
	
}
