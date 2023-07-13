package com.antwerptechnologies.pulsar.data.to.pulsar.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.pulsar.core.PulsarTopic;

@Configuration
public class PulsarTopicConfig {

	@Value("${spring.pulsar.producer.partitions}")
	private int partitions;

	@Value("${spring.pulsar.producer.topic-name}")
	private String topic;

	@Bean
	// The PulsarAdmin client automatically creates configured topics found in the context
	// This configuration is specifically added to split the topic into 3 partitions for possible concurrency
	PulsarTopic twitterTopic() {
		return PulsarTopic.builder(topic).numberOfPartitions(partitions).build();
	}
}
