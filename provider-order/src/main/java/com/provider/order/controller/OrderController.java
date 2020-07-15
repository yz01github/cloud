package com.provider.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.provider.order.entity.User;

/**
 * <p>Title: OrderController</p>  
 * <p>Description: []</p>  
 * @author youngZeu  
 * created 2019年7月27日
 */
@RestController
@RequestMapping("/peo")
public class OrderController {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${user.url}")
	private String url;

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return restTemplate.getForObject(url + id, User.class);
	}
	
	/**
	 * <p>Title: getUser</p>  
	 * <p>Description: [动态获取地址]</p>
	 * @author youngZeu 
	 * @param id
	 * @return
	 */
	@GetMapping("/poj/{id}")
	public User eurekaGetUser(@PathVariable Integer id) {
		// 通过服务名，去eureka获取服务地址
		InstanceInfo eureka = eurekaClient.getNextServerFromEureka("PROVIDER-USER", false);
		String url = eureka.getHomePageUrl();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id); 
		// 通过发现的服务地址，拼接请求和参数并发送
		return restTemplate.getForObject(url + "user/peo/entity?id={id}", User.class, map);
	}
	
	/**
	 * 使用断路器，先在主类加上@EnableCircuitBreaker,然后在需要使用的接口上加@HystrixCommand,并制定失败调用方法
	 * 注意：失败调用方法的返回值和入参必须与要使用断路器的方法一致
	 * <p>Title: ribbonGetUser</p>  
	 * <p>Description: []</p>  
	 * @author youngZeu 
	 * @param id
	 * @return
	 */
	@GetMapping("/rib/{id}")
	@HystrixCommand(fallbackMethod = "failed")
	public User ribbonGetUser(@PathVariable Integer id) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id); 
		// 使用ribbon的服务，可以直接使用服务名拼接调用，服务名代表的就是ip:端口，拼接请求和参数并发送
		// 如果调用不成功，出现PROVIDER-USER不认识问题，在restTemplate配置上加@LoadBalanced注解
		return restTemplate.getForObject("http://PROVIDER-USER/user/peo/entity?id={id}", User.class, map);
	}
	
	@GetMapping("/ribtest")
	public String ribbonTest() {
		// 获取对应服务名的服务实例，会使用负载均衡算法返回，平时也使用此获取负载均衡的服务uri
		ServiceInstance instance = loadBalancerClient.choose("PROVIDER-USER");
		System.err.println("ip: " + instance.getHost() + "\tport: " + instance.getPort());
		// 结论：ribbon负载均衡默认的算法是轮询，即A/B两个服务时，会是A-B-A-B-A-B这样的调用顺序
		return "";
	}
	
	@GetMapping("/dbtest")
	public String db() {
		return "successfly";
	}
	
	/**
	 * <p>Title: failed</p>  
	 * <p>Description: [熔断返回方法]</p>  
	 * @author youngZeu 
	 * @return
	 */
	public User failed(Integer id) {
		return new User(-11L);
	}
}
