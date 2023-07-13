package com.antwerptechnologies.pulsar.data.to.pulsar.service.messaging.impl;

import java.util.concurrent.CompletableFuture;

import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

import com.antwerptechnologies.pulsar.data.to.pulsar.service.messaging.PulsarProducer;
import com.antwerptechnologies.pulsar.pulsar.avro.model.TwitterAvroModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterPulsarProducer implements PulsarProducer<TwitterAvroModel> {

	private final PulsarTemplate<TwitterAvroModel> pulsarTemplate;

	@Override
	public void send(String topic, TwitterAvroModel message) {
		log.info("Sending message='{}' to topic='{}'", message, topic);

		try {
			CompletableFuture<MessageId> pulsarResultFuture = pulsarTemplate.sendAsync(topic, message,
					Schema.AVRO(TwitterAvroModel.class));

			addCallback(topic, message, pulsarResultFuture);

		} catch (PulsarClientException ex) {
			log.error("Pulsar client exception: {}", ex.getMessage());
		}
	}

	private void addCallback(String topicName, TwitterAvroModel message,
			CompletableFuture<MessageId> pulsarResultFuture) {
		pulsarResultFuture.whenComplete((result, ex) -> {
			if (ex != null) {
				log.error("Error while sending message {} to topic {}", message.toString(), topicName, ex);
			} else {
				log.info("Successfully sent message {} to topic {} with id {}", message.toString(), topicName,
						result.toString());
			}
		});
	}

}
