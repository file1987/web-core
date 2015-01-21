package com.studio.elephant.web.framework.system;

import java.util.List;

import org.apache.log4j.Logger;

import com.studio.elephant.web.framework.AbstractService;
import com.studio.elephant.web.framework.IService;
import com.studio.elephant.web.framework.config.ServiceConfig;
import com.studio.elephant.web.framework.config.UrlValidateConfig;
import com.studio.elephant.web.framework.model.ServiceConfigModel;
import com.studio.elephant.web.framework.model.UrlValidateModel;
import com.studio.elephant.web.framework.register.ServiceRegister;
import com.studio.elephant.web.framework.register.UrlValidateRegister;

/**
 * 系统级别的服务（必须加载）
 * 
 * @author file
 * @since 2015-1-20 下午12:08:33
 * @version 1.0
 */
public final class SystemService extends AbstractService {
	
	private static final Logger logger = Logger.getLogger(SystemService.class);
	
	
	public String getCode() {
		return "SystemService";
	}

	@Override
	protected void startuping() {
		//加载服务注册
		loadServiceConfig();
		//加载url校验配置
		loadUrlConfig();
	}

	@Override
	protected void shutdowning() {
	  //卸载所有已注册的服务
	  ServiceRegister.unregisterOnShutdownAll();
	  
	  //卸载所有已注册的url校验
	  UrlValidateRegister.unregisterAll();
	  
	}

	/**
	 * 加载服务配置
	 */
	private void loadServiceConfig() {
		
		// 注册服务配置
		ServiceConfig serviceConfig = new ServiceConfig();
		// 获取注册的服务数据
		List<ServiceConfigModel> services = serviceConfig.getRegisterService();
		// 对需要注册的服务数据进行注册并启动
		for (ServiceConfigModel model : services) {
			try {
				IService service = (IService) Class.forName(model.serviceClass).newInstance();
				service.setCode(model.serviceName);
				ServiceRegister.registerServiceOnStartup(service);
			} catch (InstantiationException e) {
				logger.error("service:" + model, e);
				throw new IllegalArgumentException(e);
			} catch (IllegalAccessException e) {
				logger.error("service:" + model, e);
				throw new IllegalArgumentException(e);
			} catch (ClassNotFoundException e) {
				logger.error("service:" + model, e);
				throw new IllegalArgumentException(e);
			}
		}
	}
	
	/**
	 * 加载url配置
	 */
	private void loadUrlConfig(){
		UrlValidateConfig  config = new UrlValidateConfig();
		List<UrlValidateModel> models = config.getRegisterURL();
		for(UrlValidateModel model:models){
			UrlValidateRegister.registerUrlParts(model.url, model.serviceCode);
		}
	}
			

}
