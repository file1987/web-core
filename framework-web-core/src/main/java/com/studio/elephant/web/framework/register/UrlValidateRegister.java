package com.studio.elephant.web.framework.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.studio.elephant.utils.StringUtil;
import com.studio.elephant.web.framework.IService;

/**
 * 通过url校验的ServiceCode
 * @author file
 * @since 2015-1-21 上午11:24:26
 * @version 1.0
 */
public final class UrlValidateRegister {
	
	private static final Map<String,String>  serviceCodes = new HashMap<String, String>();
	private static final Logger logger = Logger.getLogger(UrlValidateRegister.class);
	/**
	 * 注册校验url对应的service（正确处理是一个service对应一个url段）
	 * <br/><b>注意无需每个url都注册</b>
	 * @param url
	 * @param serviceCode
	 */
	public static void registerUrlParts(String url,String serviceCode){
		if(StringUtil.isEmpty(url)||StringUtil.isEmpty(serviceCode)){
			logger.error(String.format("params is not be null!!!! url:%s,serviceCode:%s", url,serviceCode));
			throw new NullPointerException("params is not be null!!!!");
		}
		
		if(serviceCodes.containsKey(url)){
			logger.error(String.format("url is existed,url:%s,serviceCode:%s", url,serviceCode));
			throw new IllegalArgumentException("url is existed");
		}
		
		serviceCodes.put(url, serviceCode);
	}
	/**
	 * 注销url校验
	 * @param url
	 */
	public static void unregister(String url){
		serviceCodes.remove(url);
	}
	/**
	 * 注销服务校验
	 * @param service
	 */
	public static void unregister(IService service){ 
		if(service==null){
			logger.error("service is not null!!");
			throw new NullPointerException("param is not be null!!!!");
		}
		List<Entry<String,String>> services = new ArrayList<Map.Entry<String,String>>(); 
		for(Entry<String, String> entry:serviceCodes.entrySet()){
			if(entry.getValue()!=null && StringUtil.isEquals(entry.getValue(), service.getCode())){
				services.add(entry);
			}
		}
		
		for(Entry<String, String> entry:services){
			unregister(entry.getKey());
		}
	}
	/**
	 * 根据url获取对应的servicecode
	 * @param url 
	 * @return
	 */
	public static String getServiceCode(String url){
		if(StringUtil.isEmpty(url)){
			logger.error("url is not null!!");
			throw new NullPointerException("param is not be null!!!!");
		}
		for(Entry<String, String> entry:serviceCodes.entrySet()){
			if(url.contains(entry.getKey())){
				return entry.getValue();
			}
		}
		return null;
	}
	
	
	
	
	
}
