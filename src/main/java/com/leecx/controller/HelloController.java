package com.leecx.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leecx.pojo.Resource;
import com.leecx.pojo.User;
import com.leecx.utils.LeeJSONResult;

@Controller
public class HelloController {

	//	@Value("${com.itzixi.opensource.name}")
	//	private String openSourceName;
	
	@Autowired
	private Resource resource;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hellp Spring Boot~~~~~~~";
	}
	
	@RequestMapping("/getUserJSON")
	public User getUserJSON() {
		
		User u = new User();
		u.setName("lee");
		u.setAge(18);
		
		return u;
	}
	
	@RequestMapping("/getUser")
	public LeeJSONResult getUser() {
		
		User u = new User();
		u.setName("lee");
		u.setAge(18);
		u.setPassword("123465");
		u.setBirthday(new Date());
		u.setDesc("hello world123~~~~~~");
		
		return LeeJSONResult.ok(u);
	}
	
	@RequestMapping("/hotrelease")
	public String hotrelease() {
		
		return "hotrelease~~~~~~~~~";
	}
	
	//	@RequestMapping("/openSourceName")
	//	public String openSourceName() {
	//		
	//		return openSourceName;
	//	}
	
	@RequestMapping("/getResource")
	public LeeJSONResult getResource() {
		
		Resource bean = new Resource();
		BeanUtils.copyProperties(resource, bean);
		
		return LeeJSONResult.ok(bean);
	}
	
	
}
