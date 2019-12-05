package com.hqyj.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.demo.MyInterceptor;
import com.hqyj.demo.filter.UrlFilter;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	public MyInterceptor myInterceptor;
	
	/* 
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor).addPathPatterns("/**");
	}
	
	
	
	
	/**
	 * <p>
	 * 注册过滤器
	 * </p>
	 * @author zcj
	 * @Date 2019年11月30日
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<UrlFilter> urlFilter(){
		FilterRegistrationBean<UrlFilter> filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new UrlFilter());
		
		return filterRegistrationBean;
		
	}
	

	

}
