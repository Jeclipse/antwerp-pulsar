package com.antwerptechnologies.pulsar.analytics.consumer.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "data-to-pulsar-service")
public class AnalyticsServiceConfigData {
    private Integer batchSize;
}
