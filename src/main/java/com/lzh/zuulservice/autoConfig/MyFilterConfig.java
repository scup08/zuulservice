package com.lzh.zuulservice.autoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzh.zuulservice.filter.AccessFilter;

@Configuration
public class MyFilterConfig {

	@Bean
	public AccessFilter getAccessFilter() {
		return new AccessFilter();
	}
}