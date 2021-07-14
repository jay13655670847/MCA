package com.jay.cn.service;

import com.jay.cn.api.TestApi2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
//@FeignClient(value = "EurekaProvider" )
@FeignClient(value = "EurekaProvider" ,fallback = ProviderFallBackService.class)
public interface ProviderService  extends TestApi2 {

}
