package com.leecx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.leecx.controller.interceptor.OneInterceptor;
import com.leecx.controller.interceptor.TwoInterceptor;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/one/**")
													 .addPathPatterns("/two/**");
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		
		super.addInterceptors(registry);
	}

}
