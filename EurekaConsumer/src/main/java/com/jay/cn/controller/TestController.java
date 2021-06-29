package com.jay.cn.controller;

import com.jay.cn.config.MyRestTemplate;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/hello")
    public String testHello(){

        List<ServiceInstance> instanceList = discoveryClient.getInstances("EurekaProvider8001");

        String forObject =null;

        if(instanceList.size()>0) {
            // 服务
            ServiceInstance instanceInfo = instanceList.get(0);
            System.out.printf(instanceInfo.getHost());

             forObject = restTemplate.getForObject("http://" + instanceInfo.getHost() +":"+ instanceInfo.getPort() + "/hello", String.class);

        }

//        String forObject = restTemplate.getForObject(Provider_URL + "/hello", String.class);
//        System.out.printf(forObject);


        return forObject;
    }

    @GetMapping("/consumer/hello2")
    public String test(){
        return "Hello Eureka!";
    }
}
