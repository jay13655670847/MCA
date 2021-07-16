package com.jay.cn.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "ok";
    }

    @GetMapping("/index")
    public String ok(){
        System.out.println("登录ok~~~");
        return "index";
    }

    @GetMapping("/admin/hello")
    @ResponseBody
    public String hello2(){

        return "Hello admin~~~";
    }

    @GetMapping("/user/hello")
    @ResponseBody
    public String hello3(){

        return "Hello user~~~";
    }
}
