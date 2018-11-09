package com.leecx.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
 
    Logger logger = LoggerFactory.getLogger(LoginController.class);
 
    @RequestMapping(value = "/login.page", method = RequestMethod.GET)
    public String login() {
        logger.info("=============================");
        logger.info("====登录处理逻辑==============");
        logger.info("=============================");
        return "apps/login";
    }
}

