package com.jay.cn.controller;

import com.jay.cn.api.TestApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2  implements TestApi {


    @Override
    public String testHello() {
        return "Hello Api 8001";
    }
}
