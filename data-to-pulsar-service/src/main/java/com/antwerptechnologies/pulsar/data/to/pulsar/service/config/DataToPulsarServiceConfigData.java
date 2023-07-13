package com.antwerptechnologies.pulsar.data.to.pulsar.service.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "data-to-pulsar-service")
public class DataToPulsarServiceConfigData {
	private List<String> twitterKeywords;
	private Boolean enableMockTweets;
	private Long mockSleepMs;
	private Integer mockMinTweetLength;
	private Integer mockMaxTweetLength;
}

