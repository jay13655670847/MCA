package com.jay.cn.controller;

import com.jay.cn.service.ProviderFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ProviderFeginService providerFeginService;

    @GetMapping("/consumer/fegin/hello")
    public String testHello(){

        return providerFeginService.test();
    }

    @GetMapping("/consumer/fegin/hello2")
    public String testHello2(){

        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        return providerFeginService.testHello();
    }
}
