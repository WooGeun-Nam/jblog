package com.douzone.jblog.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class ApplicationContextEventListener {
	@Autowired
	private ApplicationContext applicationContext;
	
	@EventListener({ContextRefreshedEvent.class})
	public void handleContextRefreshedEvent() {
		System.out.println("--- Context Refresh Event Received ---" + applicationContext);
		
		InternalResourceViewResolver viewResolver = applicationContext.getBean(InternalResourceViewResolver.class);
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setExposedContextBeanNames("site");
	}
}
