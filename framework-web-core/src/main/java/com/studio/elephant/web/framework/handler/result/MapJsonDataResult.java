package com.studio.elephant.web.framework.handler.result;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.studio.elephant.utils.StringUtil;
import com.studio.elephant.web.framework.handler.IResult;
/**
 * Map容器的json格式的返回结果
 * @author file
 * @since 2015-3-28 上午11:31:38
 * @version 1.0
 */
public class MapJsonDataResult implements IResult {
	
	Map<String,Object>  jsonMap = Maps.newHashMap();
	
	
	public void putMapParam(String key,Object value){
		if(StringUtil.isEmpty(key)){
			key = "null";
		}
		jsonMap.put(key, value);
	}
	
	
	public void putMapParams(String[] keys,Object[] values){
		
		if(keys==null||values==null){
			return;
		}
		int keyLen = keys.length;
		int valueLen = values.length;
		int minLen = (keyLen < valueLen?keyLen:valueLen);
		
		for(int i=0;i<minLen;i++){
			putMapParam(keys[i], values[i]);
		}		
	}
	
		
	@Override
	public String data() {
		if(jsonMap.isEmpty())
			return "";
		return JSON.toJSONString(jsonMap);
	}

}
