package com.microservice.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class ConfigBeans {

	@Bean
	public Logger logger(){
		return Logger.getGlobal();
	}

}
