package com.jay.cn.controller;

import com.jay.cn.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hystrix")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderService providerService;

    @RequestMapping("/hello1")
    public String hello1(){

        String forObject = restTemplate.getForObject("http://EurekaProvider/hello3/hello", String.class);
        return forObject;
    }

    @RequestMapping("/hello2")
    @HystrixCommand(fallbackMethod = "hello1FallBack")
    public String hello2(){

        String forObject = providerService.hello2();
        return forObject;
    }


    public String hello1FallBack(){
        return "系统繁忙，请歇会哦~~~~~~~~~~~~~~~";
    }

}
