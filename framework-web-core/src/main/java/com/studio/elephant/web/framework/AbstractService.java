package com.studio.elephant.web.framework;

import org.apache.log4j.Logger;

/**
 * 抽象服务
 * @author file
 * @since 2015-1-13 下午12:04:04
 * @version 1.0
 */
public abstract class AbstractService implements IService {
	

	/**
	 * 服务状态，默认为正在启动
	 */
	protected volatile ServiceStatus status = ServiceStatus.Start;
	
	private static final Logger logger = Logger.getLogger(AbstractService.class);
	
	public void onStartup() {
		if(status!=ServiceStatus.Start){
			logger.error("服务的状态不能正确进入启动状态！！请确认状态："+getStatus(),new IllegalAccessException());
			return;
		}
		startuping();
		status = ServiceStatus.Running;
	}
	/**
	 * 启动服务逻辑
	 */
	protected abstract void startuping();

	public void onShutdown() {
		if(status!=ServiceStatus.Running){
			logger.error("服务的状态不能正确进入停止状态！！请确认状态:"+getStatus(),new IllegalAccessException());
			return;
		}
		shutdowning();
		status = ServiceStatus.Stop;
	}
	/**
	 * 卸载服务逻辑
	 */
	protected abstract void shutdowning();
	

	public void onPause() {
		if(status!=ServiceStatus.Running){
			logger.error("服务的状态不能正确进入暂停状态！！请确认状态:"+getStatus(),new IllegalAccessException());
			return;
		}
		status = ServiceStatus.Pause;
	}

	public void onResume() {
		if(status!=ServiceStatus.Pause){
			logger.error("服务的状态不能正确进入恢复状态！！请确认状态:"+getStatus(),new IllegalAccessException());
			return;
		}
		status = ServiceStatus.Running;
	}

	public ServiceStatus getStatus() {
		return status;
	}
	
	private volatile String code;
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public void setCode(String code) {
		this.code = code;
	}
	

}
