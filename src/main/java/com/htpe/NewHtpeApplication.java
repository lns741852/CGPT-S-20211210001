package com.htpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class NewHtpeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewHtpeApplication.class, args);
	}

}
