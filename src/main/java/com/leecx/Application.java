package com.leecx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.leecx.mapper")
// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.leecx", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
// 开启异步调用方法
@EnableAsync
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

//mybatis 数据库操作异常：
//tk.mybatis.mapper.provider.base.BaseInsertProvider
//import org.mybatis.spring.annotation.MapperScan;
//SpringBoot启动错误原因是：
/** springboot启动时会自动注入数据源和配置jpa 
 * 解决：在@SpringBootApplication中排除其注入 
 * @SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
 **/
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})