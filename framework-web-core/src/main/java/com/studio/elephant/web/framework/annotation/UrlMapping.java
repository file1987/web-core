package com.studio.elephant.web.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * url 映射表(注解)
 * @author file
 * @since 2015-3-23 下午3:52:03
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlMapping {
	 /**
	  * 路径
	  * @return
	  */
	 String  mapping()  ; //url
}
