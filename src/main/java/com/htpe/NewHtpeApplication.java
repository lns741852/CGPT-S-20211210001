package com.htpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class NewHtpeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(NewHtpeApplication.class, args);
	}
	
	@Override//打包springboot项目
	protected SpringApplicationBuilder configure(
	            SpringApplicationBuilder builder) {
	    return builder.sources(this.getClass());
	}

}
