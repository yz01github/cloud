package com.provider.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient		// 将当前项目标记为客户端
@EnableCircuitBreaker
public class OrderApp{
	
    public static void main(String[] args){
        SpringApplication.run(OrderApp.class, args);
    }
}
