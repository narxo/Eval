package com.cegeka.evaluations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cegeka.evaluations")
@SpringBootApplication
public class EvaluationsApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(EvaluationsApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EvaluationsApplication.class);
    }
}
