package com.jay.cn.controller;

import com.jay.cn.api.TestApi2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHello3 implements TestApi2 {

    @RequestMapping("/hello")
    public String hello(){

        return "Hello Hystrix!  8001";
    }

    @Override
    public String hello2() {
        return "Hello Hystrix2222!     8001";
    }
}
