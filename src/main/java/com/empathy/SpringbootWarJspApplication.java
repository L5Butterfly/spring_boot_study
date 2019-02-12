package com.empathy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import tk.mybatis.spring.annotation.MapperScan;


/*
@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.leecx.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.leecx", "org.n3r.idworker"})
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
*/
public class SpringbootWarJspApplication extends SpringBootServletInitializer {
	
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    return builder.sources(SpringbootWarJspApplication.class);
	  }
	 
	
	/**
	* 配置JSP视图解析器
	* 如果没有配置视图解析器。Spring会使用BeanNameViewResolver，通过查找ID与逻辑视图名称匹配且实现了View接口的beans
	* 
	* @return
	*/
	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		/** 设置视图路径的前缀 */
		resolver.setPrefix("/WEB-INF/jsp/");
		/** 设置视图路径的后缀 */
		resolver.setSuffix(".jsp");
		return resolver;
	}

	  public static void main(String[] args) {
	    SpringApplication.run(SpringbootWarJspApplication.class, args);
	  }

}
