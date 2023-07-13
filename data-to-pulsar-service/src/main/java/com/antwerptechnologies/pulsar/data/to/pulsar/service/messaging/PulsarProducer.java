package com.antwerptechnologies.pulsar.data.to.pulsar.service.messaging;


import org.apache.avro.specific.SpecificRecordBase;

public interface PulsarProducer<T extends SpecificRecordBase> {
	
	void send(String topic, T message);

}
