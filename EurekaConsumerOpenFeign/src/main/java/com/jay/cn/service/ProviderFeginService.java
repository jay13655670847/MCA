package com.jay.cn.service;

import com.jay.cn.api.TestApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Component
@FeignClient(value = "EurekaProvider")
public interface ProviderFeginService extends TestApi {

    @GetMapping("/hello")
    public String test();
}
