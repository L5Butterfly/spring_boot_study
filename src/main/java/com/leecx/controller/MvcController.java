package com.leecx.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mvc")
public class MvcController {
	
	 
	/**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     * 可以通过localhost:8080或者localhost:8080/index访问该方法
     */

	@RequestMapping(value ="index")
    public String index(Map<String, Object> model){
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        model.put("time", new Date());
        model.put("message", "hello my mvc jsp"); 
        return "index";
    }
	
	
	
    @RequestMapping("/index2")
    public ModelAndView page1(){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("message", "hello spring boot");
        return mav;
    }

    
    @RequestMapping("/index3")
    public String index3(Map<String,Object> map){
        map.put("name", "HelloController");
        return "index";
    }
}
