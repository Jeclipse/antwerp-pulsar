package com.antwerptechnologies.pulsar.analytics.consumer.service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antwerptechnologies.pulsar.analytics.consumer.service.entity.AnalyticsEntity;

public interface AnalyticsRepository extends JpaRepository<AnalyticsEntity, UUID>,
        AnalyticsCustomRepository<AnalyticsEntity, UUID> {

    //@Query(value = "select e from AnalyticsEntity e where e.word=:word order by e.recordDate desc")
    //List<AnalyticsEntity> getAnalyticsEntitiesByWord(@Param("word") String word, Pageable pageable);
	List<AnalyticsEntity> findByTrendingTopic(String topic);
}
