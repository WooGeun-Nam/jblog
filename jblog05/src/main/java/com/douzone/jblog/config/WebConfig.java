package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.douzone.jblog.config.web.FileuploadConfig;
import com.douzone.jblog.config.web.MessageResourceConfig;
import com.douzone.jblog.config.web.MvcConfig;
import com.douzone.jblog.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan({"com.douzone.jblog.controller"})
@Import({MvcConfig.class,SecurityConfig.class,MessageResourceConfig.class,FileuploadConfig.class})
public class WebConfig {
}
