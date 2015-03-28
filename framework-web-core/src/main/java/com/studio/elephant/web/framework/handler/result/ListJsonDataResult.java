package com.studio.elephant.web.framework.handler.result;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.studio.elephant.web.framework.handler.IResult;
/**
 * List容器的json格式的返回结果
 * @author file
 * @since 2015-3-28 下午12:11:51
 * @version 1.0
 */
public class ListJsonDataResult implements IResult {
	
	private List<Object>  jsonList = Lists.newArrayList();
	
	public void addParam(Object o){
		if(o==null)
			return;
		jsonList.add(o);
	}
	
	
	public void addParams(Object ... o){
		if(o==null)
			return;
		for(Object _o:o){
			addParam(_o);
		}
	}
	
	
	@Override
	public String data() {
		if(jsonList.isEmpty())
			return "";
		return JSONObject.toJSONString(jsonList);
	}

}
