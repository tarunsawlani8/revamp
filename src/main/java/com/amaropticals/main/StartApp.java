package com.amaropticals.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages={"com.amaropticals.controller", "com.amaropticals.restcontroller", "com.amaropticals.dao"})
//@EnableAutoConfiguration (exclude = { DataSourceAutoConfiguration.class })
public class StartApp extends SpringBootServletInitializer {
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(StartApp.class);
	    }

	public static void main(String[] args) throws Exception {

		SpringApplication.run(StartApp.class, args);
	}

}
