package com.lzh.zuulservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.zuulservice.filter.AccessFilter;
import com.lzh.zuulservice.filter.TestFilter;

@SpringBootApplication
@EnableEurekaClient
//@Import({MyFilterConfig.class})
@EnableZuulProxy
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.lzh.zuulservice.persistence", annotationClass = MyBatisRepository.class)
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }
    
//    @Bean
//	public TestFilter getTestFilter() {
//		return new TestFilter();
//	}
    
//    @Bean
//	public ErrorFilter getErrorFilter() {
//		return new ErrorFilter();
//	}
//    @Bean
//	public AccessFilter getAccessFilter() {
//		return new AccessFilter();
//	}
}