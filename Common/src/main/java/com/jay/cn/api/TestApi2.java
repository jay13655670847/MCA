package com.jay.cn.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public interface TestApi2 {

    @RequestMapping("/hello4/hello2")
    public String hello2();
}
