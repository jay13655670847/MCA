package com.jay.cn.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TestApi {

    @GetMapping("/api/hello")
    public String testHello();

}
