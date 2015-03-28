package com.studio.elephant.web.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 服务注册映射
 * @author file
 * @since 2015-3-28 上午10:51:54
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMapping {
	/**
	 * 服务代码
	 * @return 服务代码
	 */
	public String serviceCode();
}
