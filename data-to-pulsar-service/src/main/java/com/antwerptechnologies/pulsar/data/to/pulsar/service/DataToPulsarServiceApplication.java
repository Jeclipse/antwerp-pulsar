package com.antwerptechnologies.pulsar.data.to.pulsar.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.antwerptechnologies.pulsar.data.to.pulsar.service.runner.StreamRunner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.antwerptechnologies.pulsar")
public class DataToPulsarServiceApplication implements CommandLineRunner {


    private final StreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(DataToPulsarServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App starts...");
        streamRunner.start();
    }
}
