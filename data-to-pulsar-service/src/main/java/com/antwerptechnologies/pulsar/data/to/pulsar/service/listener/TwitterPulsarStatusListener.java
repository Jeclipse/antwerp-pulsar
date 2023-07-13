package com.antwerptechnologies.pulsar.data.to.pulsar.service.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.antwerptechnologies.pulsar.data.to.pulsar.service.messaging.PulsarProducer;
import com.antwerptechnologies.pulsar.data.to.pulsar.service.transformer.TwitterStatusToAvroTransformer;
import com.antwerptechnologies.pulsar.pulsar.avro.model.TwitterAvroModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
@Slf4j
@RequiredArgsConstructor
public class TwitterPulsarStatusListener extends StatusAdapter {


    private final PulsarProducer<TwitterAvroModel> pulsarProducer;

    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;
    
    @Value("${spring.pulsar.producer.topic-name}")
    private String topic;


    @Override
    public void onStatus(Status status) {
        log.info("Received status text {} sending to pulsar topic {}", status.getText(), topic);
        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        pulsarProducer.send(topic, twitterAvroModel);
    }
}
