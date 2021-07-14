package com.jay.cn.controller;

import com.jay.cn.api.TestApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class TestController2  implements TestApi {


    @Override
    public String testHello() {
        return "Hello Api 8001";
    }

    @Override
    public String testHello2(String id) {
        return id;
    }

    @Override
    public Map testHello3(String id, String name) {
        return Collections.singletonMap(id,name);
    }

    @Override
    public Map testHello4(Map map) {
        return map;
    }

    @Override
    public Map testHello5(Map map) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return map;
    }
}
