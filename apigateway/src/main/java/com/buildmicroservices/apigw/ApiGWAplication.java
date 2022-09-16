package com.buildmicroservices.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiGWAplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGWAplication.class, args);
    }
}
