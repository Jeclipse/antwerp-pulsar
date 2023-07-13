package com.antwerptechnologies.pulsar.analytics.consumer.service.transformer;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import com.antwerptechnologies.pulsar.analytics.consumer.service.entity.AnalyticsEntity;
import com.antwerptechnologies.pulsar.pulsar.avro.model.TwitterAvroModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvroToDbEntityModelTransformer {

	private final IdGenerator idGenerator;

	public AnalyticsEntity getEntityModel(List<TwitterAvroModel> avroModels) {

		// Generate a new Id
		UUID id = idGenerator.generateId();

		// Calculate average tweet length by words
		Double averageTweetLength = avroModels.stream()
				.mapToDouble(avroModel -> avroModel.getText().trim().split("\\s+").length)
				.average()
				.orElse(0);

		// Calculate the most trending topic
		Map<String, Long> topicCounts = avroModels.stream()
				.flatMap(avroModel -> Stream.of(avroModel.getText().trim().split("\\s+")))
				.filter(word -> List.of("Java", "Sports", "Food", "Politics").contains(word))
				.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

		String mostTrendingTopic = topicCounts.entrySet().stream()
				.filter(entry -> entry.getValue() == 1)
				.max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

		// Calculate the number of times the most trending topic occurred in the list
		Map.Entry<String, Long> maxEntry = topicCounts.entrySet()
		        .stream()
		        .max(Map.Entry.comparingByValue())
		        .orElse(null);

		// Assign the value to a Long if it exists
		Long topicOccurrences = (maxEntry != null) ? maxEntry.getValue() : 0L;

		// Calculate the number of words in all the tweet texts in the list
		Integer totalWords = avroModels.stream()
				.mapToInt(avroModel -> avroModel.getText().trim().split("\\s+").length)
				.sum();
		
		log.info("Analytics calculated for entity of id {}", id);
		
        return new AnalyticsEntity(id, averageTweetLength, mostTrendingTopic, topicOccurrences, totalWords);
        
	}

}
