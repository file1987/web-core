package com.studio.elephant.web.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studio.elephant.utils.StringUtil;
import com.studio.elephant.web.framework.IService;
import com.studio.elephant.web.framework.ServiceStatus;
import com.studio.elephant.web.framework.register.ServiceRegister;
import com.studio.elephant.web.framework.register.UrlValidateRegister;
/**
 * <p>基础过滤器</p>
 * <hr>
 * 1.编码处理<br/>
 * 2.根据url的规则进行统一处理Service请求:http:xxxx/xxxx/...../serviceCode/xxxx?....
 * @author file
 * @since 2015-1-15 下午4:40:15
 * @version 1.0
 */
public class ElephantFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType( "text/html" );
		response.setCharacterEncoding("utf-8");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String uri = httpRequest.getRequestURI();
		
		String serviceCode = UrlValidateRegister.getServiceCode(uri);
		if(!StringUtil.isEmpty(serviceCode)){
			IService service = ServiceRegister.getService(serviceCode);
			//获取不到注册的服务或者该服务器不是运行状态
			if(service==null||service.getStatus()!=ServiceStatus.Running){
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
