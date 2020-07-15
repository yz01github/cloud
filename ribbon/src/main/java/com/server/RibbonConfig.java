package com.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * <p>Title: RibbonConfig</p>  
 * <p>Description: [负载均衡算法配置]</p>  
 * @author youngZeu  
 * created 2019年7月28日
 *  注意：这个类不能放在可以被启动类和@ComponentScan扫描到的地方，否则这个配置会被所有的ribbon客户端共享，也就达不到特殊化指定目的
 */
//@Configuration
public class RibbonConfig {

	/**
	 * <p>Title: ribbonRule</p>  
	 * <p>Description: [配置负载均衡算法]</p>  
	 * @author youngZeu 
	 * @param config
	 * @return
	 */
	@Bean
	public IRule ribbonRule() {
		// TODO 这里存在问题，修改了负载算法，并没有起到作用
		return new RandomRule();// 随机算法
		// RoundRobinRule(轮询算法)
		
		// AvailabilityFilteringRule()会先过滤由于多次访问故障而处于断路器跳闸状态的服务，
		// 还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
		
		// WeightedResponseTimeRule()	根据平均响应的时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高，
		// 刚启动时如果统计信息不足，则使用轮询策略，等统计信息足够会切换到WeightedResponseTimeRule
		
		// RetryRule()：先按照轮询的策略获取服务，如果获取失败则在制定时间内进行重试，获取可用的服务。
		
		// BestAviableRule()：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
		
		// ZoneAvoidanceRule()：默认规则，符合判断server所在区域的性能和server的可用性选择服务器
	}
}
