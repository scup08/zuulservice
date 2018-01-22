package com.lzh.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lzh.zuulservice.filter.ErrorFilter;
import com.lzh.zuulservice.filter.TestFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }
    
    @Bean
	public TestFilter getTestFilter() {
		return new TestFilter();
	}
    
    @Bean
	public ErrorFilter getErrorFilter() {
		return new ErrorFilter();
	}
    
}