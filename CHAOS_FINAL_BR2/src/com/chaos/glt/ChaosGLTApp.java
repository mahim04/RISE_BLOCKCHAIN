package com.chaos.glt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.chaos.glt.controller")
@EnableAutoConfiguration
public class ChaosGLTApp  extends SpringBootServletInitializer 
{

	    public static void main(String[] args) 
	    {
	    	System.out.println("Let's inspect the beans provided by Spring Boot:");
	        SpringApplication.run(ChaosGLTApp.class, args);
	    }
	    
	    
	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(ChaosGLTApp.class);
	    }

}