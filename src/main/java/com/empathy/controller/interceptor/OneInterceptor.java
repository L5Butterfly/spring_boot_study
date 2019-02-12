package com.empathy.controller.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.empathy.utils.JsonUtils;
import com.empathy.utils.LeeJSONResult;


/**
 * 自定义拦截器实现
 * @author Administrator
 *
 */
public class OneInterceptor implements HandlerInterceptor  {

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		if (true) {
			returnErrorResponse(response, LeeJSONResult.errorMsg("被one拦截..."));
		}
		
		System.out.println("被one拦截...");
		
		return true;
	}
	
	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void returnErrorResponse(HttpServletResponse response, LeeJSONResult result) throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
			//统一控制层输出格式统一，对象转化JSON,编码格式统一
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}
}
