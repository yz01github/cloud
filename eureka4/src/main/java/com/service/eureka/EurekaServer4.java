package com.service.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer		//将当前项目标记为eureka server
public class EurekaServer4 {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer4.class, args);
	}
}
