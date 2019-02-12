package com.empathy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ftl")
public class FreemarkerController {

	@RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "itzixi");
        return "freemarker/index";
    }
	
	@RequestMapping("center")
    public String center() {
        return "freemarker/center/center";
    }

}