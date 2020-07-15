package com.provider.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.provider.user.entity.User;

@RestController
@RequestMapping("/peo")
public class UserController {

	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping("/{id}")
	public User addUser(@PathVariable Long id, HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		return new User(id);
	}

	@GetMapping("/info")
	public String getUser() {
		// 通过服务名，获取eureka实例
		InstanceInfo eureka = eurekaClient.getNextServerFromEureka("PROVIDER-ORDER", false);
		return eureka.getHomePageUrl();
	}

	@GetMapping("/entity")
	public User getEntity(Long id, HttpServletRequest request) {
		System.err.println(request.getServerName() + " port: " + request.getServerPort());
		return new User(id);
	}
}
