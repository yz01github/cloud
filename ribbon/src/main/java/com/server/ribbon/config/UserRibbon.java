package com.server.ribbon.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

import com.server.RibbonConfig;

//@Configuration
//启用ribbon，并对PROVIDER-USER进行负载均衡,使用RibbonConfig中配置的负载均衡算法
//@RibbonClient(name = "PROVIDER-USER", configuration = RibbonConfig.class) 
public class UserRibbon {

}
