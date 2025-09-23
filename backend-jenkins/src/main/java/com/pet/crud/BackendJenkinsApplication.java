package com.pet.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackendJenkinsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendJenkinsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendJenkinsApplication.class, args);
        System.out.println("Project is Running ...");
    }
}
