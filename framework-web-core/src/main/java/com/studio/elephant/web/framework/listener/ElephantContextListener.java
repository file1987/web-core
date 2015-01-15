package com.studio.elephant.web.framework.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.studio.elephant.web.framework.IService;
import com.studio.elephant.web.framework.config.ServiceConfig;
import com.studio.elephant.web.framework.model.ServiceConfigModel;
import com.studio.elephant.web.framework.register.ServiceRegister;
/**
 * 
 * @author file
 * @since 2015-1-15 下午4:15:57
 * @version 1.0
 */
public class ElephantContextListener implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(ElephantContextListener.class);
	
	public void contextInitialized(ServletContextEvent sce) {
		//注册服务配置
		ServiceConfig serviceConfig = new ServiceConfig();
		//获取注册的服务数据
		List<ServiceConfigModel> services = serviceConfig.getRegisterService();
		//对需要注册的服务数据进行注册并启动
		for (ServiceConfigModel model : services) {
			try {
				IService service = (IService) Class.forName(model.serviceClass).newInstance();
				ServiceRegister.registerServiceOnStartup(service);
			} catch (InstantiationException e) {
				logger.error("service:"+model, e);
				throw new IllegalArgumentException(e);
			} catch (IllegalAccessException e) {
				logger.error("service:"+model, e);
				throw new IllegalArgumentException(e);
			} catch (ClassNotFoundException e) {
				logger.error("service:"+model, e);
				throw new IllegalArgumentException(e);
			}
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {
		//停止所有服务并注销
		ServiceRegister.unregisterOnShutdownAll();
	}

}
