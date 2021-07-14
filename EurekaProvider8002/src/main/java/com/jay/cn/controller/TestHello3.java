package com.jay.cn.controller;

import com.jay.cn.api.TestApi2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestHello3 implements TestApi2 {

    AtomicInteger count = new AtomicInteger();

    @RequestMapping("/hello")
    public String hello(){

        return "Hello Hystrix!  8002";
    }

    @Override
    public String hello2() {
        int i = count.getAndIncrement();

        System.out.println("第几次调用："+i);

        int ii=1/0;
        return "Hello Hystrix2222!   8002";
    }
}
