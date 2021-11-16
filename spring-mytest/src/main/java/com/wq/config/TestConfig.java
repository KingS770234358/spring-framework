package com.wq.config;

import com.wq.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestConfig {
	@Bean
	public User user(){
		return new User("001","smart哥");
	}

}
