package com.antwerptechnologies.pulsar.analytics.consumer.service.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// We can use the record java feature here instead but a dto with lombok is convenient
public class AnalyticsResponseModel {
	private UUID id;
	private Double averageTweetLength;
	private String trendingTopic;
	private Long trendingTopicCount;
	private Integer wordCount;

}
