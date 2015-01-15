package com.studio.elephant.web.framework;
/**
 * 服务状态
 * @author file
 * @since 2015-1-13 下午12:02:29
 * @version 1.0
 */
public enum ServiceStatus {
	/**正在启动**/
	Start,
	/**进入运行**/
	Running,
	/**停止/卸载服务**/
	Stop,
	/**暂停/挂起服务**/
	Pause;
}
