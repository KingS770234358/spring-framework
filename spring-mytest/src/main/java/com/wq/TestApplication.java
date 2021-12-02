package com.wq;

import com.wq.config.TestConfig;
import com.wq.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApplication {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		User user = (User) context.getBean("user");
		System.out.println(user);
	}
}
