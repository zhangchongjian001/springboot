package com.hqyj.demo;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor{
	private final static Logger LOGGER=LoggerFactory.getLogger(MyInterceptor.class);

	/**
	 *重写拦截点之前执行的方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("start interceptor=====================================");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 *拦截点执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String uriString=request.getServletPath();
		if (null == modelAndView || modelAndView.getViewName().startsWith("redirect")) {
			return;
		}
		if (modelAndView.getModelMap().get("template")== null) {
			if (uriString.startsWith("/")) {
			uriString.substring(1);
			}
			modelAndView.addObject("template",uriString.toLowerCase());
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 *拦截点之后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("end interceptor==========================");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
