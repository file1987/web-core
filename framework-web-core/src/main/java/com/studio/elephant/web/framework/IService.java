package com.studio.elephant.web.framework;
/**
 * 服务接口
 * @author file
 * @since 2015-1-13 上午11:52:47
 * @version 1.0
 */
public interface IService {
	/**
	 * 启动服务
	 */
	public void onStartup();
	/**
	 * 停止服务
	 */
	public void onShutdown();
	/**
	 * 当前服务的当前状态
	 * @return
	 */
	public ServiceStatus getStatus();
	/**
	 * 暂停服务
	 */
	public void onPause();
	/**
	 * 恢复服务
	 */
	public void onResume();
	
	/**
	 * 服务的唯一码
	 * @return
	 */
	public String getCode();
	
	
}
