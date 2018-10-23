package com.banary.cache.guava;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/13 上午11:05
 */
public interface CacheService<K, V> {

    void put(K key, V value);

    V get(K key);

    V get(K key, CacheCallback<V> cacheCallback, Object...context);

    void delete(K key);
}
