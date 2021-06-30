package com.jay.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaProviderMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderMain8002.class);
    }
}
