package com.antwerptechnologies.pulsar.analytics.consumer.service.messaging.impl;

import java.util.List;

import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.pulsar.listener.AckMode;
import org.springframework.stereotype.Service;

import com.antwerptechnologies.pulsar.analytics.consumer.service.entity.AnalyticsEntity;
import com.antwerptechnologies.pulsar.analytics.consumer.service.messaging.PulsarConsumer;
import com.antwerptechnologies.pulsar.analytics.consumer.service.repository.AnalyticsRepository;
import com.antwerptechnologies.pulsar.analytics.consumer.service.transformer.AvroToDbEntityModelTransformer;
import com.antwerptechnologies.pulsar.pulsar.avro.model.TwitterAvroModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyticsPulsarConsumer implements PulsarConsumer<TwitterAvroModel> {

	private final AvroToDbEntityModelTransformer avroToDbEntityModelTransformer;

	private final AnalyticsRepository analyticsRepository;

	// Define a pulsar consumer of concurrency level 3 and shared subscription type compatible with our 3 partition topic
	// This way our consumers can consume concurrently with max throughput
	@Override
	@PulsarListener(subscriptionName = "tweet-topic-subscription", topics = "tweet-topic", schemaType = SchemaType.AVRO, batch = true,
			                             subscriptionType = SubscriptionType.Shared, concurrency = "3", ackMode = AckMode.BATCH)
	public void receive(List<TwitterAvroModel> messages) {
		log.info("{} messages received", messages.size());
		// Generate Analytics for every batch of tweets and persist the analytics data
		AnalyticsEntity twitterAnalyticsEntity = avroToDbEntityModelTransformer.getEntityModel(messages);
		analyticsRepository.persist(twitterAnalyticsEntity);
		log.info("analytics entity persisted to db with id {}", twitterAnalyticsEntity.getId());
	}

}