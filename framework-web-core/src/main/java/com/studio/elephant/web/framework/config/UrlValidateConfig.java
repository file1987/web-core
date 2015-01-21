package com.studio.elephant.web.framework.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.studio.elephant.utils.StringUtil;
import com.studio.elephant.web.framework.model.UrlValidateModel;
/**
 * 在过滤器进行校验的url配置处理
 * @author file
 * @since 2015-1-21 下午3:24:37
 * @version 1.0
 */
public class UrlValidateConfig implements IConfig {

	private static final Logger logger = Logger.getLogger(UrlValidateConfig.class);

	/**
	 * 读取配置文件中的所有URL校验
	 * @return
	 */
	public List<UrlValidateModel> getURLs() {
		URL url = this.getClass().getClassLoader().getResource(path());
		File myXML = new File(url.getPath());
		SAXReader sr = new SAXReader();
		List<UrlValidateModel> urls = new ArrayList<UrlValidateModel>();
		try {
			Document doc = sr.read(myXML);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> serviceIt = root.elementIterator();
			while (serviceIt.hasNext()) {
				Element service = serviceIt.next();
				UrlValidateModel urlModel = new UrlValidateModel();
				urlModel.url = service.attributeValue("url");
				urlModel.serviceCode = service.attributeValue("serviceCode");
				String register = service.attributeValue("register");
				urlModel.isRegister = "true".equals(register)||StringUtil.isEmpty(register);
				urls.add(urlModel);
			}
		} catch (DocumentException e) {
			logger.error("", e);
		} finally {

		}

		return urls;
	}

	/**
	 * 获取配置文件中标志为注册的URL
	 * 
	 * @return
	 */
	public List<UrlValidateModel> getRegisterURL() {
		URL url = this.getClass().getClassLoader().getResource(path());
		File myXML = new File(url.getPath());
		SAXReader sr = new SAXReader();
		List<UrlValidateModel> urls = new ArrayList<UrlValidateModel>();
		try {
			Document doc = sr.read(myXML);
			Element root = doc.getRootElement();

			@SuppressWarnings("unchecked")
			Iterator<Element> serviceIt = root.elementIterator();

			while (serviceIt.hasNext()) {
				Element service = serviceIt.next();
				UrlValidateModel urlModel = new UrlValidateModel();
				urlModel.url = service.attributeValue("url");
				urlModel.serviceCode = service.attributeValue("serviceCode");
				String register = service.attributeValue("register");
				urlModel.isRegister = "true".equals(register)||StringUtil.isEmpty(register);
				if (urlModel.isRegister)
					urls.add(urlModel);
			}
		} catch (DocumentException e) {
			logger.error("", e);
		} finally {

		}
		return urls;
	}

	@Override
	public String path() {
		return "urlvalidates.xml";
	}

}
