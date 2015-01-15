package com.studio.elephant.web.framework.model;
/**
 * 注册服务配置模型
 * @author file
 * @since 2015-1-14 下午4:52:56
 * @version 1.0
 */
public final class ServiceConfigModel {
	
	@Override
	public String toString() {
		return "ServiceConfigModel [serviceName=" + serviceName + ", serviceClass=" + serviceClass + ", isRegister=" + isRegister + "]";
	}
	public String serviceName;
	public String serviceClass;
	public boolean isRegister;

}
