package com.jay.cn.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){

        return "Hello~~~";
    }


    @GetMapping("/login.do")
    public String login(){
        System.out.println("登录~~~");
        return "login";
    }
}
