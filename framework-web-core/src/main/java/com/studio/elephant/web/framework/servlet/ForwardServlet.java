package com.studio.elephant.web.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studio.elephant.web.framework.handler.IResult;
import com.studio.elephant.web.framework.handler.http.IHttpHandler;
import com.studio.elephant.web.framework.handler.throwable.IExceptionHandler;
import com.studio.elephant.web.framework.register.GlobalRegister;
import com.studio.elephant.web.framework.register.HttpHandlerRegister;
/**
 * 转发servlet<br/>
 * <p>1.监听所有路径的servlet</p>
 * <p>2.根据url注册的handler，进行分发<p>
 * @author file
 * @since 2015-3-28 上午11:02:06
 * @version 1.0
 */
public class ForwardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp, false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp, true);
	}
	
	/**
	 * 执行业务
	 * @param request 请求
	 * @param response 响应
	 * @param isPost  是否isPost提交
	 * @throws IOException
	 */
	private  void   doService(HttpServletRequest request, HttpServletResponse response,boolean isPost) throws IOException{
		String  url  = request.getRequestURI();
		IHttpHandler httpHandler =  HttpHandlerRegister.getHttpHandler(url);
		if(httpHandler==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}else{
			
			IResult result = null;
			try{
				if(isPost){
					result = httpHandler.doPost(request, response);
				}else{
					result = httpHandler.doGet(request, response);
				}	
			}catch(Exception e){
				IExceptionHandler exceptionHandler = GlobalRegister.getExceptionHandler();
				if(exceptionHandler==null){
					//TODO 如果没有设置全局异常捕获，则加个默认的
				}else{
					exceptionHandler.exceptionCaught(e, httpHandler);
				}
			}
			
			if(result!=null){
				response.getWriter().write(result.data());
				response.getWriter().flush();
				response.getWriter().close();
			}
			
		}
		
	}
	
	

}
