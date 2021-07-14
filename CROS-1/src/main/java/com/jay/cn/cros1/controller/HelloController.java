package com.jay.cn.cros1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("来了~~");
        return "Hello~~";
    }


    @RequestMapping("/login.do")
    public String login(){
        System.out.println("登录~~");
        return "login";
    }

    @GetMapping("/success.do")
    public String ok() {
        System.out.println("success~~");
        return "ok";
    }
}
