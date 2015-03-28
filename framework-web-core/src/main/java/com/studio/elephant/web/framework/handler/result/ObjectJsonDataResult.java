package com.studio.elephant.web.framework.handler.result;

import com.alibaba.fastjson.JSON;
import com.studio.elephant.web.framework.handler.IResult;
/**
 * 对象容器的json格式的返回结果
 * @author file
 * @since 2015-3-28 下午12:04:35
 * @version 1.0
 */
public class ObjectJsonDataResult implements IResult {
	
	private Object object;
	
	
	
	public ObjectJsonDataResult(Object object) {
		this.object = object;
	}

	public ObjectJsonDataResult() {
	}

	public void setObject(Object object){
		this.object = object;
	}
	
	

	@Override
	public String data() {
		if(object==null)
			return "";
		return JSON.toJSONString(object);
	}

}
