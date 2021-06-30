package com.jay.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SpringApplicationMain7001 {
   
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationMain7001.class);
    }

}
