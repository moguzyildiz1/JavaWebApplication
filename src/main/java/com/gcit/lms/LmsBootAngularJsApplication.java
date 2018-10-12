package com.gcit.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.gcit.lms")
public class LmsBootAngularJsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsBootAngularJsApplication.class, args);
	}
	
//	@Bean
//	public ViewResolver contentNegotiatingViewResolver() {
//	    ContentNegotiatingViewResolver resolver =
//	            new ContentNegotiatingViewResolver();
//
//	    List<View> views = new ArrayList<>();
//	    views.add(new MappingJackson2XmlView());
//	    views.add(new MappingJackson2JsonView());
//
//	    resolver.setDefaultViews(views);
//	    return resolver;
//	}
}

