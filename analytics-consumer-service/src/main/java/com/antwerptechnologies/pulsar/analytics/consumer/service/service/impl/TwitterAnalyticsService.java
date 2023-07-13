package com.antwerptechnologies.pulsar.analytics.consumer.service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antwerptechnologies.pulsar.analytics.consumer.service.dto.AnalyticsResponseModel;
import com.antwerptechnologies.pulsar.analytics.consumer.service.repository.AnalyticsRepository;
import com.antwerptechnologies.pulsar.analytics.consumer.service.service.AnalyticsService;
import com.antwerptechnologies.pulsar.analytics.consumer.service.transformer.EntityToResponseModelTransformer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwitterAnalyticsService implements AnalyticsService {

	private final AnalyticsRepository analyticsRepository;

    private final EntityToResponseModelTransformer entityToResponseModelTransformer;

	@Override
	public List<AnalyticsResponseModel> findByTrendingTopic(String topic) {
	    return analyticsRepository.findByTrendingTopic(topic).stream()
	            .map(entityToResponseModelTransformer::getResponseModel)
	            .toList();
	}

	@Override
	public List<AnalyticsResponseModel> getAllAnalytics() {
	    return analyticsRepository.findAll().stream()
	            .map(entityToResponseModelTransformer::getResponseModel)
				.toList();

	}
}
