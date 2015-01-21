package com.studio.elephant.web.framework.system;

import com.studio.elephant.web.framework.AbstractService;
/**
 * 系统级别的服务（必须加载）
 * @author file
 * @since 2015-1-20 下午12:08:33
 * @version 1.0
 */
public final class SystemService extends AbstractService {

	public String getCode() {
		return "SystemService";
	}

	@Override
	protected void startuping() {
		

	}

	@Override
	protected void shutdowning() {
		

	}

}
