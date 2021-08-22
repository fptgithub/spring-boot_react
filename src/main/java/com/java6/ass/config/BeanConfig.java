package com.java6.ass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfig {

	@Bean
	public ObjectMapper objBean() {
		return new ObjectMapper();
	}
	
}
