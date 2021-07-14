package com.jay.cn.controller;

import com.jay.cn.service.ProviderFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        return providerFeginService.testHello();
    }

    @GetMapping("/consumer/fegin/hello3")
    public String testHello3(@RequestParam("id")String id){

        return providerFeginService.testHello2(id);
    }

    @GetMapping("/consumer/fegin/hello4")
    public Map testHello4(@RequestParam("id")String id, @RequestParam("name")String name){

        return providerFeginService.testHello3(id,name);
    }

    @GetMapping("/consumer/fegin/hello5")
    public Map testHello5(@RequestParam("id")String id, @RequestParam("name")String name){

        HashMap<String, Object> map= new HashMap<>(2);

        map.put("id", 2000);
        map.put("name", "凯");

        return providerFeginService.testHello4(map);
    }

    @GetMapping("/consumer/fegin/hello6")
    public Map testHello6(@RequestParam("id")String id, @RequestParam("name")String name){

        HashMap<String, Object> map = new HashMap<>(2);

        map.put("id", 2000);
        map.put("name", "凯");

        return providerFeginService.testHello5(map);
    }

    @GetMapping("/consumer/fegin/hello7")
    public String testHello7(String id){

        return providerFeginService.testHello2(id);
    }

    private AtomicInteger count = new AtomicInteger();

    @GetMapping("/consumer/fegin/hello8")
    public String testHello8(){

        int i = count.getAndIncrement();
        System.out.println("第"+i+"次调用..........");


        return providerFeginService.testHello();
    }
}
