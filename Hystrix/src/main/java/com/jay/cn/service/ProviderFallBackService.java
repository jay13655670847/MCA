package com.jay.cn.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProviderFallBackService implements  ProviderService{
    @Override
    public String hello2() {
        return "系统繁忙，请稍后再试！~~~~~~~~~~~~~~";
    }
}
