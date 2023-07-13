package com.antwerptechnologies.pulsar.analytics.consumer.service.transformer;

import org.springframework.stereotype.Component;

import com.antwerptechnologies.pulsar.analytics.consumer.service.dto.AnalyticsResponseModel;
import com.antwerptechnologies.pulsar.analytics.consumer.service.entity.AnalyticsEntity;

@Component
public class EntityToResponseModelTransformer {

    public AnalyticsResponseModel getResponseModel(AnalyticsEntity twitterAnalyticsEntity) {
    	
        return AnalyticsResponseModel
                .builder()
                .id(twitterAnalyticsEntity.getId())
                .averageTweetLength(twitterAnalyticsEntity.getAverageTweetLength())
                .trendingTopic(twitterAnalyticsEntity.getTrendingTopic())
                .trendingTopicCount(twitterAnalyticsEntity.getTrendingTopicCount())
        		.wordCount(twitterAnalyticsEntity.getWordCount())
                .build();
    }
}
