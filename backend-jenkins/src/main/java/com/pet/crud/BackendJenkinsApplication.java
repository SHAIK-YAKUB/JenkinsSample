package com.pet.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendJenkinsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BackendJenkinsApplication.class, args);
		System.out.println("Project is Running ...");
		
	}

}
