package com.empathy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理系统主页信息
 * @author Administrator
 *
 */

@Controller
public class HomeController {

	/**
	 * 系统主页
	 * @return
	 */
	@RequestMapping("/home.do")
	public String home() {
		return "apps/center";
	}
	
	
	/**
	 * 系统退出
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout() {
		return "apps/login";
	}
	
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login() {
		return "apps/login";
	}
	
	
	/**
	 * 系统主页
	 * @return
	 */
	@RequestMapping("/home")
	public String home3() {
		return "apps/center";
	}
	
	
	/**
	 * 系统退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String out3() {
		return "apps/login";
	}
}
