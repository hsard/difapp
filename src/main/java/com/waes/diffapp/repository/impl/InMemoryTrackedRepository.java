package com.waes.diffapp.repository.impl;

import static java.util.Objects.nonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.waes.diffapp.model.ExpirableBean;
import com.waes.diffapp.repository.ExpirableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * In-memory implementation of ExpirableReposotory, storing entries within ConcurrentHashMap.
 * Scheduled job is executed to regularly check for expired beans and remove them from the map.
 *
 * @param <K> class of Key object
 * @param <V> class of value object, must extend {@link ExpirableBean}
 * @author HS
 */

@Slf4j
public class InMemoryTrackedRepository<K, V extends ExpirableBean> implements ExpirableRepository<K, V> {
    private final ConcurrentMap<K, V> objectEntries = new ConcurrentHashMap<>(8, 0.9f, 1);
    private ScheduledExecutorService scheduler;

    @Value("${diffapp.diff.session.timeoutMs}")
    private Long timeoutMs;

    @PostConstruct
    private void init() {
        scheduler = new ScheduledThreadPoolExecutor(1);
        Runnable cleaner = () -> objectEntries.keySet().forEach(key -> {
            V entity = objectEntries.get(key);
            if (isSessionExpired(entity)) {
                remove(key);
            }
        });
        scheduler.scheduleAtFixedRate(cleaner, 0L, 10L, TimeUnit.SECONDS);
    }

    @PreDestroy
    private void cleanUp() {
        scheduler.shutdownNow();
    }

    @Override
    public Optional<V> get(K key) {
        V entity = objectEntries.get(key);
        if (nonNull(entity)) {
            if (isSessionExpired(entity)) {
                log.warn("Removing outdated entity, key = {}", key);
                objectEntries.remove(key);
            } else {
                refreshSession(entity);
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    @Override
    public V add(K key, V entity) {
        Assert.notNull(key, "key must not be null.");
        log.info("Adding entity into repository, key = {}", key);
        objectEntries.put(key, entity);
        refreshSession(entity);
        return entity;
    }

    @Override
    public void remove(K key) {
        objectEntries.remove(key);
    }

    @Override
    public boolean isSessionExpired(V entity) {
        return (System.currentTimeMillis() - entity.getLastAccessedTime()) > timeoutMs;
    }

    @Override
    public void refreshSession(V entity) {
        entity.setLastAccessedTime(System.currentTimeMillis());
    }
}
