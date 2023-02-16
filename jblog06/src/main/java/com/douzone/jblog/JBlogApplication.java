package com.douzone.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.douzone.jblog.event.ApplicationContextEventListener;

@SpringBootApplication
public class JBlogApplication {

	// ApplicationContextEventListener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JBlogApplication.class, args);
	}
}
