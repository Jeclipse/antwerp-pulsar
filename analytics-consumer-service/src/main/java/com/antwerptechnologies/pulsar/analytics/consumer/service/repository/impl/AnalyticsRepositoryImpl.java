package com.antwerptechnologies.pulsar.analytics.consumer.service.repository.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.antwerptechnologies.pulsar.analytics.consumer.service.entity.BaseEntity;
import com.antwerptechnologies.pulsar.analytics.consumer.service.repository.AnalyticsCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AnalyticsRepositoryImpl<T extends BaseEntity<PK>, PK> implements AnalyticsCustomRepository<T, PK> {


    @PersistenceContext
    protected EntityManager em;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:100}")
    protected int batchSize;
    
    protected static int currentBatchCount = 0;


    @Override
    @Transactional
    public <S extends T> PK persist(S entity) {
        this.em.persist(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public <S extends T> void batchPersist(Collection<S> entities) {
        if (entities.isEmpty()) {
            log.info("No entity found to insert!");
            return;
        }
        int batchCnt = 0;
        for (S entity : entities) {
        	log.trace("Persisting entity with id {}", entity.getId());
            this.em.persist(entity);
            batchCnt++;
            if (batchCnt % batchSize == 0) {
                this.em.flush();
                this.em.clear();
            }
        }
        if (batchCnt % batchSize != 0) {
            this.em.flush();
            this.em.clear();
        }
    }

    @Override
    @Transactional
    public <S extends T> S merge(S entity) {
        return this.em.merge(entity);
    }

    @Override
    @Transactional
    public <S extends T> void batchMerge(Collection<S> entities) {
        if (entities.isEmpty()) {
        	log.info("No entity found to insert!");
            return;
        }
        int batchCnt = 0;
        for (S entity : entities) {
        	log.trace("Merging entity with id {}", entity.getId());
            this.em.merge(entity);
            batchCnt++;
            if (batchCnt % batchSize == 0) {
                this.em.flush();
                this.em.clear();
            }
        }
        if (batchCnt % batchSize != 0) {
            this.em.flush();
            this.em.clear();
        }
    }

    @Override
    public void clear() {

    }

}
