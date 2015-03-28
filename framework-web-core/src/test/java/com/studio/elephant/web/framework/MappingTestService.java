package com.studio.elephant.web.framework;

import com.studio.elephant.web.framework.annotation.ServiceMapping;

@ServiceMapping(serviceCode = "MappingTestService")
public class MappingTestService extends AbstractService {

	@Override
	public String getCode() {
		return "MappingTestService";
	}

	@Override
	protected void startuping() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void shutdowning() {
		// TODO Auto-generated method stub
		
	}

	

}
