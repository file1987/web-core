package com.studio.elephant.web.framework.annotation;

import com.studio.elephant.web.framework.register.Global;

/**
 * 全局注册注解
 * @author file
 * @since 2015-3-28 下午3:13:47
 * @version 1.0
 */
public @interface GlobalRegisterMapping {
	/**
	 * 获取全局枚举
	 * @return
	 */
	public Global global();
}
