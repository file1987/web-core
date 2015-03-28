package com.studio.elephant.web.framework.register;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;
import com.studio.elephant.web.framework.handler.http.IHttpHandler;

/**
 * httpHandler 注册器
 * @author file
 * @since 2015-3-28 下午5:40:47
 * @version 1.0
 */
public final class HttpHandlerRegister {
	
	private static final Map<String,IHttpHandler> httpHandlers = Maps.newHashMap();
	/**
	 * 注册url对应处理的httpHandler
	 * @param url url
	 * @param httpHandler  httpHandler
	 */
	public static void registerHandler(String url,IHttpHandler httpHandler){
		if(url==null||httpHandler==null){
			throw new IllegalArgumentException("url或httpHandler为空");
		}
		if(httpHandlers.containsKey(url)){
			throw new IllegalArgumentException("url的映射已存在，不可重复注册相同的url");
		}
		
		httpHandlers.put(url, httpHandler);
	}
	/**
	 * 根据url获取相应的处理httpHandler
	 * @param url  url
	 * @return  httpHandler
	 */
	public static IHttpHandler getHttpHandler(String url){
		return httpHandlers.get(url);
	}
	/**
	 * 获取所有的httpHandler
	 * @return
	 */
	public static Collection<IHttpHandler> getHttpHandlers(){
		return Collections.unmodifiableCollection(httpHandlers.values());
	}

}
