package com.antwerptechnologies.pulsar.analytics.consumer.service.messaging;

import java.util.List;

import org.apache.avro.specific.SpecificRecordBase;

public interface PulsarConsumer<T extends SpecificRecordBase> {
    void receive(List<T> messages);
}
