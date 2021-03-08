package com.homsdev.app.interceptor;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor {

	private static final Logger log= Logger.getLogger(ProcessingTimeLogInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String queryString=request.getQueryString()== null ? "":"?"+request.getRequestURL();
		String path= request.getRequestURL()+queryString;
		Long startTime=(Long)request.getAttribute("startTime");
		Long endTime= System.currentTimeMillis();
		log.info(String.format("%s millisecond taken to process the requets %s ",(endTime-startTime), path));
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
