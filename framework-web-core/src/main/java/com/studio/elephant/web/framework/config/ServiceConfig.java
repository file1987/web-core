package com.studio.elephant.web.framework.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.studio.elephant.web.framework.model.ServiceConfigModel;

/**
 * 服务注册
 * @author file
 * @since 2015-1-14 下午4:36:29
 * @version 1.0
 */
public class ServiceConfig implements IConfig {
	
	/**
	 * 获取配置文件的所有服务
	 * @return
	 */
	public List<ServiceConfigModel>  getServices(){
		 URL url = this.getClass().getClassLoader().getResource(path());
		 File myXML = new File(url.getPath());  
         SAXReader sr = new SAXReader();
         List<ServiceConfigModel>  services = new ArrayList<ServiceConfigModel>();
        try {  
            Document doc  =  sr.read(myXML);  
            Element root = doc.getRootElement();
            
            @SuppressWarnings("unchecked")
			Iterator<Element> serviceIt = root.elementIterator();
            
            while (serviceIt.hasNext()) {
				Element service = serviceIt.next();
				ServiceConfigModel serviceConfigModel = new ServiceConfigModel();
				serviceConfigModel.serviceName = service.attributeValue("name");
				serviceConfigModel.serviceClass = service.attributeValue("class");
				String register = service.attributeValue("register");
				serviceConfigModel.isRegister = "true".equals(register);
				services.add(serviceConfigModel);
			}
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } finally{ 
        	
        }
		
		return services;
	}
	
	/**
	 * 获取配置文件中标志为注册的服务
	 * @return
	 */
	public List<ServiceConfigModel> getRegisterService(){
		 URL url = this.getClass().getClassLoader().getResource(path());
		 File myXML = new File(url.getPath());  
         SAXReader sr = new SAXReader();
         List<ServiceConfigModel>  services = new ArrayList<ServiceConfigModel>();
        try {  
            Document doc  =  sr.read(myXML);  
            Element root = doc.getRootElement();
            
            @SuppressWarnings("unchecked")
			Iterator<Element> serviceIt = root.elementIterator();
            
            while (serviceIt.hasNext()) {
				Element service = serviceIt.next();
				ServiceConfigModel serviceConfigModel = new ServiceConfigModel();
				serviceConfigModel.serviceName = service.attributeValue("name");
				serviceConfigModel.serviceClass = service.attributeValue("class");
				String register = service.attributeValue("register");
				serviceConfigModel.isRegister = "true".equals(register);
				if(serviceConfigModel.isRegister)
					services.add(serviceConfigModel);
			}
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } finally{ 
        	
        }
		return services;
	}
	
	public String path() {
		return "services.xml";
	}

}
