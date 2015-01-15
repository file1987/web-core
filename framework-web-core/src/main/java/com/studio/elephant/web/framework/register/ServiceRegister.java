package com.studio.elephant.web.framework.register;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.studio.elephant.web.framework.IService;
/**
 * 服务注册器
 * @author file
 * @since 2015-1-14 下午4:10:13
 * @version 1.0
 */
public final class ServiceRegister {
	/**
	 * 服务容器
	 */
	private static final Map<String,IService>  services = new HashMap<String,IService>();
	
	private static final Logger logger = Logger.getLogger(ServiceRegister.class);
	/**
	 * 注册服务
	 * @param service
	 */
	public static void registerService(IService service){
		if(service==null){
			logger.error("the Service is null!fucking.......");
			throw new NullPointerException("service 不能为null");
		}
			
		if(services.containsKey(service.getCode())){
			logger.error("the Service code be used~~~记得service code要全局唯一哦~~~重新来一发呗~~~");
			throw new IllegalArgumentException("记得service code要全局唯一哦~~~重新来一发呗~~~");
		}
		
		services.put(service.getCode(), service);
	}
	
	/**
	 * 注册并启动服务
	 * @param service
	 */
	public static void registerServiceOnStartup(IService service){
		//注册服务
		registerService(service);
		//启动服务
		service.onStartup();
	}
	
	
	
	/**
	 * 获取所有已注册的服务
	 * @return
	 */
	public static Collection<IService> getAllService(){
		return Collections.unmodifiableCollection(services.values());
	}
	/**
	 * 注销服务
	 * @param service
	 */
	public static void unregisterService(IService service){
		if(service==null){
			logger.error("the Service is null!fucking.......");
			throw new NullPointerException("service 不能为null");
		}
		services.remove(service.getCode());
	}
	
	/**
	 * 注销并停止服务
	 * @param service
	 */
	public static void unregisterServiceOnShutdown(IService service){
		//注销服务
		unregisterService(service);
		//停止服务
		service.onShutdown();
	}
	
	/**
	 * 注销所有服务
	 */
	public static void unregisterAll(){
		services.clear();
	}
	
	/**
	 * 停止所有服务，并注销
	 */
	public  static void unregisterOnShutdownAll(){
		for(IService service:services.values()){
			service.onShutdown();
		}
		unregisterAll();
	}
	
	
	/**
	 * 通过服务的唯一码获取服务
	 * @param code
	 * @return
	 */
	public static IService getService(String code){
		return services.get(code);
	}
	
}
