package com.antwerptechnologies.pulsar.analytics.consumer.service.service;


import java.util.List;

import com.antwerptechnologies.pulsar.analytics.consumer.service.dto.AnalyticsResponseModel;

public interface AnalyticsService {

    List<AnalyticsResponseModel> findByTrendingTopic(String topic);
    List<AnalyticsResponseModel> getAllAnalytics();
}

