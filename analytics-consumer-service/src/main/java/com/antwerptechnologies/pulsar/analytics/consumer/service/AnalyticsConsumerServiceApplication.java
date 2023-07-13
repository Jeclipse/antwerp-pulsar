package com.antwerptechnologies.pulsar.analytics.consumer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.antwerptechnologies.pulsar"})
public class AnalyticsConsumerServiceApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsConsumerServiceApplication.class, args);
    }

}
