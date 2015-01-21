package com.studio.elephant.web.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.studio.elephant.web.framework.IService;
import com.studio.elephant.web.framework.register.ServiceRegister;
import com.studio.elephant.web.framework.system.SystemService;
/**
 * ContextListener
 * @author file
 * @since 2015-1-15 下午4:15:57
 * @version 1.0
 */
public class ElephantContextListener implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(ElephantContextListener.class);
	//全局只有一个服务级别的系统服务
	private IService  sysService = null;
	
	
	public void contextInitialized(ServletContextEvent sce) {
		if(logger.isDebugEnabled())
			logger.debug("register system service begin");
		
		//必须启动系统级别服务(把其他服务放到系统服务去处理)
		ServiceRegister.registerServiceOnStartup(sysService = new SystemService());
		
		if(logger.isDebugEnabled())
			logger.debug("register system service end");
		

	}

	public void contextDestroyed(ServletContextEvent sce) {
		
		if(logger.isDebugEnabled())
			logger.debug("unregister system service begin");
		//注销并停止系统服务
		ServiceRegister.unregisterServiceOnShutdown(sysService);
		if(logger.isDebugEnabled())
			logger.debug("unregister system service end");
		
	}

}
