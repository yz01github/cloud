package com.service.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Title: WebSecurityConfig</p>  
 * <p>Description: [开启security验证功能后服务注册失败解决配置]</p>  
 * @author youngZeu  
 * created 2019年7月27日
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Spring Cloud 2.0 以上的security默认启用了csrf检验
    	// 要在eurekaServer端配置security的csrf检验为false
        http.csrf().disable();
        super.configure(http);
    }
}